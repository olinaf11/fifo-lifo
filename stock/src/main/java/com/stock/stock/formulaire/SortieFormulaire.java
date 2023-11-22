package com.stock.stock.formulaire;

import java.sql.Date;

public class SortieFormulaire {
    Date dateSortie;
    Double quantite;
    String idMagasin;
    String idArticle;
    Integer etat;

    public Date getDateSortie() {
        return dateSortie;
    }
    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }
    public Double getQuantite() {
        return quantite;
    }
    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }
    public String getIdMagasin() {
        return idMagasin;
    }
    public void setIdMagasin(String idMagasin) {
        this.idMagasin = idMagasin;
    }
    public String getIdArticle() {
        return idArticle;
    }
    public void setIdArticle(String idArticle) {
        this.idArticle = idArticle;
    }
    public Integer getEtat() {
        return etat;
    }
    public void setEtat(Integer etat) {
        this.etat = etat;
    }


}
