package com.stock.stock.mapping;

import java.sql.Connection;

import annotation.Column;
import annotation.PrimaryKey;
import annotation.Table;
import dao.BddObject;

@Table(name = "magasin")
public class Magasin extends BddObject<Magasin>{
    @PrimaryKey(length = 7, prefix = "MAG", sequence = "seq_magasin")
    @Column
    String id;
    @Column
    String nom;

    public String getId() {
        return id;
    }
    public void setId(String id) throws Exception {
        if (id.equals(null) || id.equals("")) {
            throw new Exception("L'id ne peut pas être vide");
        }
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) throws Exception {
        if (nom.equals(null) || nom.equals("")) {
            throw new Exception("Le nom ne peut pas être vide");
        }
        this.nom = nom;
    }

    public Sortie getDernierSortieValider(Connection con, String idArticle) throws Exception{
        String script = "select * from sortie where date_sortie = (select max(date_sortie) from sortie) and id_article like '"+idArticle+"';";
        Sortie sortie = new Sortie();
        return sortie.executeQuery(con, script, sortie).get(0);
    }

}
