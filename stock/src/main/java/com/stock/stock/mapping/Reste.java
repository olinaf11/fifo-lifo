package com.stock.stock.mapping;

import java.sql.Date;

import annotation.Column;
import annotation.Table;
import dao.BddObject;

@Table(name = "v_reste")
public class Reste extends BddObject<Reste>{
    @Column(name = "id_entre")
    String idEntre;
    @Column(name = "id_article")
    String idArticle;
    @Column(name = "id_magasin")
    String idMagasin;
    @Column(name = "prix_unitaire")
    String prixUnitaire;
    @Column
    Double reste;
    @Column
    Date date;

    public String getIdEntre() {
        return idEntre;
    }
    public void setIdEntre(String idEntre) {
        this.idEntre = idEntre;
    }
    public String getIdArticle() {
        return idArticle;
    }
    public void setIdArticle(String idArticle) {
        this.idArticle = idArticle;
    }
    public String getIdMagasin() {
        return idMagasin;
    }
    public void setIdMagasin(String idMagasin) {
        this.idMagasin = idMagasin;
    }
    public String getPrixUnitaire() {
        return prixUnitaire;
    }
    public void setPrixUnitaire(String prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
    public Double getReste() {
        return reste;
    }
    public void setReste(Double reste) {
        this.reste = reste;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
