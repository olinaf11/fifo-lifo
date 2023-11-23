package com.stock.stock.mapping;

import java.sql.Connection;

import annotation.Column;
import annotation.PrimaryKey;
import annotation.Table;
import dao.BddObject;

@Table(name = "article")
public class Article extends BddObject<Article>{
    @Column
    @PrimaryKey(length = 7, prefix = "ART", sequence = "seq_article")
    String id;
    @Column
    Integer type;
    @Column
    String unite;
    @Column
    String nom;
    @Column
    String code;

    public String getId() {
        return id;
    }
    public void setId(String id) throws Exception {
        if (id.equals(null) || id.equals("")) {
            throw new Exception("L'id ne doit pas être vide");
        }
        this.id = id;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public int getType() {
        return type;
    }
    public String getUnite() {
        return unite;
    }
    public void setUnite(String unite) {
        this.unite = unite;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) throws Exception {
        if (nom.equals(null) || nom.equals("")) {
            throw new Exception("Le nom ne doit pas être vide");
        }
        this.nom = nom;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) throws Exception {
        if (code.equals(null) || code.equals("")) {
            throw new Exception("Le nom ne doit pas être vide");
        }
        this.code = code;
    }

    public Sortie getDernierSortieValider(Connection con, String idMagasin) throws Exception {
        Magasin magasin = new Magasin();
        magasin.setId(idMagasin);
        magasin.findById(con, idMagasin);
        return magasin.getDernierSortieValider(con, getId());
    }
}
