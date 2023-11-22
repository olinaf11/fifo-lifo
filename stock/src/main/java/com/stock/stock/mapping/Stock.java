package com.stock.stock.mapping;

import java.sql.Connection;
import java.sql.Date;

public class Stock {
    String idArticle;
    String idMagasin;
    Double qteInitiale;
    Double reste;
    Double prixUnitaire;
    Double montant;
    Date entre;
    Date sortie;

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
    public Double getQteInitiale() {
        return qteInitiale;
    }
    public void setQteInitiale(Double qteInitiale) {
        this.qteInitiale = qteInitiale;
    }
    public Double getReste() {
        return reste;
    }
    public void setReste(Double reste) {
        this.reste = reste;
    }
    public Double getPrixUnitaire() {
        return prixUnitaire;
    }
    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
    public Double getMontant() {
        return montant;
    }
    public void setMontant(Double montant) {
        this.montant = montant;
    }
    public Date getEntre() {
        return entre;
    }
    public void setEntre(Date entre) {
        this.entre = entre;
    }
    public Date getSortie() {
        return sortie;
    }
    public void setSortie(Date sortie) {
        this.sortie = sortie;
    }

    public Magasin getMagasin(Connection con) throws Exception{
        return (Magasin) new Magasin().findById(con, idMagasin);
    }
    public Article getArticle(Connection con) throws Exception{
        return (Article) new Article().findById(con, idArticle);
    }
}
