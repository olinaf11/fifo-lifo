CREATE TABLE article(
   id VARCHAR(7) ,
   code varchar(7),
   nom VARCHAR(100) ,
   type INTEGER,
   unite VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id)
);

CREATE TABLE magasin(
   id VARCHAR(7) ,
   nom VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id)
);

CREATE TABLE sortie(
   id VARCHAR(7) ,
   date_sortie date NOT NULL,
   quantite DOUBLE PRECISION NOT NULL,
   id_magasin VARCHAR(7) ,
   id_article VARCHAR(7) ,
   etat int,
   PRIMARY KEY(id),
   FOREIGN KEY(id_magasin) REFERENCES magasin(id),
   FOREIGN KEY(id_article) REFERENCES article(id)
);

CREATE TABLE entree(
   id VARCHAR(7) ,
   date_entree date NOT NULL,
   quantite DOUBLE PRECISION NOT NULL,
   prix_unitaire DOUBLE PRECISION NOT NULL,
   id_magasin VARCHAR(7) ,
   id_article VARCHAR(7) ,
   PRIMARY KEY(id),
   FOREIGN KEY(id_magasin) REFERENCES magasin(id),
   FOREIGN KEY(id_article) REFERENCES article(id)
);

CREATE TABLE mouvement(
   id varchar(7),
   id_sortie VARCHAR(7) ,
   id_entree VARCHAR(7) ,
   quantite DOUBLE PRECISION NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_sortie) REFERENCES sortie(id),
   FOREIGN KEY(id_entree) REFERENCES entree(id)
);

create table validation(
    id varchar(7) primary key,
    id_sortie varchar(7) references sortie(id),
    date date
);

create sequence seq_article
    start with 1
    increment by 1
    maxvalue 9999;

create sequence seq_entre
    start with 1
    increment by 1
    maxvalue 9999;

create sequence seq_sortie
    start with 1
    increment by 1
    maxvalue 9999;

create sequence seq_magasin
    start with 1
    increment by 1
    maxvalue 9999;

create sequence seq_mouvement
    start with 1
    increment by 1
    maxvalue 9999;

create sequence seq_validation
    start with 1
    increment by 1
    maxvalue 9999;

create or replace view v_valider as
select sortie.*, v.id id_validation, date
from sortie join validation v on sortie.id = v.id_sortie;

CREATE OR REPLACE VIEW v_mouvement AS
SELECT m.id,s.date date_sortie, s.id_article, m.quantite, s.id_magasin, m.id_entree, s.id id_sortie
FROM mouvement m
    JOIN v_valider s ON m.id_sortie = s.id;

CREATE OR REPLACE VIEW v_somme_mouvement as
SELECT id_entree, SUM(quantite) as quantite
FROM v_mouvement
GROUP BY id_entree;

CREATE OR REPLACE VIEW v_etat_stock AS
SELECT e.id, e.date_entree, (e.quantite - COALESCE(m.quantite, 0)) as quantite, e.prix_unitaire, e.id_magasin, e.id_article
FROM v_somme_mouvement m
    RIGHT JOIN entree e ON m.id_entree = e.id
    JOIN article art ON art.id = e.id_article
WHERE (e.quantite - COALESCE(m.quantite, 0)) > 0;

CREATE OR REPLACE VIEW v_reste_stock AS
SELECT id_article,id_magasin,sum(quantite) quantite,sum(quantite*prix_unitaire) montant
FROM v_etat_stock GROUP BY id_magasin,id_article;

CREATE OR REPLACE VIEW v_produit_distinct AS
SELECT DISTINCT art.id id_article, art.code, art.nom nom_article, art.type, mg.id id_magasin, mg.nom as nom_magasin
FROM article art
    JOIN entree ent ON art.id = ent.id_article
    JOIN magasin mg ON ent.id_magasin = mg.id
    GROUP BY mg.id,art.id;

CREATE OR REPLACE VIEW v_quantite_stock AS
SELECT e.id, e.date_entree, s.date_sortie, (e.quantite - COALESCE(m.quantite, 0)) as quantite, e.prix_unitaire, e.id_magasin, e.id_article
FROM v_mouvement m
    RIGHT JOIN entree e ON m.id_entree = e.id
    LEFT JOIN v_valider s ON m.id_sortie = s.id;
-- WHERE (e.quantite - COALESCE(m.quantite, 0)) > 0;

CREATE OR REPLACE VIEW v_etat_reste AS
SELECT e.id id_entre, s.id id_sortie, e.id_article, e.id_magasin, e.prix_unitaire, e.quantite entree, COALESCE(m.quantite, 0) sortie, COALESCE(s.date, e.date_entree) as date, e.date_entree as date_entree
FROM entree e
    LEFT JOIN mouvement m ON m.id_entree = e.id
    LEFT JOIN v_valider s ON m.id_sortie = s.id;

CREATE OR REPLACE VIEW v_reste AS
SELECT e.id, e.id_article, e.id_magasin, e.prix_unitaire, COALESCE(e.quantite - m.quantite, e.quantite) AS reste, COALESCE(s.date_sortie, e.date_entree) date FROM entree e
    LEFT JOIN mouvement m ON m.id_entree = e.id
    LEFT JOIN v_valider s ON m.id_sortie = s.id
WHERE COALESCE(e.quantite - m.quantite, e.quantite) > 0;

