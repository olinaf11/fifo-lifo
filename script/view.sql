

CREATE OR REPLACE VIEW v_somme_mouvement as
SELECT id_entree, SUM(quantite) as quantite
FROM v_mouvement
GROUP BY id_entree;

CREATE OR REPLACE VIEW v_etat_stock AS
SELECT e.id_entree, e.date_entree, s.date_sortie, (e.quantite - COALESCE(m.quantite, 0)) as quantite, e.prix_unitaire, e.id_magasin, e.id_article
FROM v_somme_mouvement m
    RIGHT JOIN entree e ON m.id_entree = e.id_entree
    JOIN article art ON art.id_article = e.id_article
WHERE (e.quantite - COALESCE(m.quantite, 0)) > 0;

CREATE OR REPLACE VIEW v_reste_stock AS
SELECT id_article,id_magasin,sum(quantite) quantite,sum(quantite*prix_unitaire) montant
FROM v_etat_stock GROUP BY id_magasin,id_article;


CREATE OR REPLACE VIEW v_produit_distinct AS
SELECT DISTINCT art.id_article, art.code, art.nom nom_article, art.type, mg.id_magasin, mg.nom as nom_magasin
FROM article art
    JOIN entree ent ON art.id_article = ent.id_article
    JOIN magasin mg ON ent.id_magasin = mg.id_magasin
    GROUP BY mg.id_magasin,art.id_article;

CREATE OR REPLACE VIEW v_quantite_stock AS
SELECT e.id_entree, e.date_entree, s.date_sortie, (e.quantite - COALESCE(m.quantite, 0)) as quantite, e.prix_unitaire, e.id_magasin, e.id_article
FROM v_mouvement m
    RIGHT JOIN entree e ON m.id_entree = e.id_entree
    JOIN sortie s ON m.id_sortie = s.id_sortie;
-- WHERE (e.quantite - COALESCE(m.quantite, 0)) > 0;

CREATE OR REPLACE VIEW v_etat_reste AS
SELECT e.id_entree, e.id_article, e.id_magasin, e.prix_unitaire, e.quantite entree, COALESCE(m.quantite, 0) sortie, COALESCE(s.date_sortie, e.date_entree) as date, e.date_entree as date_entree
FROM entree e
    LEFT JOIN mouvement m ON m.id_entree = e.id_entree
    LEFT JOIN sortie s ON m.id_sortie = s.id_sortie;

CREATE OR REPLACE VIEW v_reste AS
SELECT e.id_entree, e.id_article, e.id_magasin, e.prix_unitaire, COALESCE(e.quantite - m.quantite, e.quantite) AS reste, COALESCE(s.date_sortie, e.date_entree) date FROM entree e
    LEFT JOIN mouvement m ON m.id_entree = e.id_entree
    LEFT JOIN sortie s ON m.id_sortie = s.id_sortie
WHERE COALESCE(e.quantite - m.quantite, e.quantite) > 0;
