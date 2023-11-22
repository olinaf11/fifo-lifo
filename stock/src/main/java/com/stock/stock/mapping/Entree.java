package com.stock.stock.mapping;

import java.sql.Date;
import java.util.List;

import annotation.Column;
import annotation.PrimaryKey;
import annotation.Table;
import dao.BddObject;

@Table(name = "entree")
public class Entree extends BddObject<Entree>{
    @PrimaryKey(length = 7, prefix = "ENT", sequence = "seq_entree")
    @Column
    String id;
    @Column(name = "date_entree")
    Date dateEntree;
    @Column
    Double quantite;
    @Column(name = "prix_unitaire")
    Double prixUnitaire;
    @Column(name = "id_magasin")
    String idMagasin;
    @Column(name = "id_article")
    String idArticle;

    public String getId() {
        return id;
    }
    public void setId(String id) throws Exception {
        if (id.equals(null) || id.equals("")) {
            throw new Exception("L'id ne peut pas être vide");
        }
        this.id = id;
    }
    public Date getDateEntree() {
        return dateEntree;
    }
    public void setDateEntree(Date dateEntree) {
        this.dateEntree = dateEntree;
    }
    public Double getQuantite() {
        return quantite;
    }
    public void setQuantite(Double quantite) throws Exception {
        if (quantite.doubleValue() < 0) {
            throw new Exception("La quantite doit être positif");
        }
        this.quantite = quantite;
    }
    public Double getPrixUnitaire() {
        return prixUnitaire;
    }
    public void setPrixUnitaire(Double prixUnitaire) throws Exception {
        if (prixUnitaire < 0) {
            throw new Exception("Le prix unitaire doit être positif");
        }
        this.prixUnitaire = prixUnitaire;
    }
    public String getIdMagasin() {
        return idMagasin;
    }
    public void setIdMagasin(String idMagasin) throws Exception {
        if (idMagasin.equals(null) || idMagasin.equals("")) {
            throw new Exception("L'id du magasin ne peut pas être vide");
        }
        this.idMagasin = idMagasin;
    }
    public String getIdArticle() {
        return idArticle;
    }
    public void setIdArticle(String idArticle) throws Exception {
        if (idArticle.equals(null) || idArticle.equals("")) {
            throw new Exception("L'id de l'article ne peut pas être vide");
        }
        this.idArticle = idArticle;
    }

    public void setQuantite(String quantite) throws Exception {
        if (quantite.equals(null) || quantite.equals("")) {
            throw new Exception("La quantité ne peut pas être vide");
        }
        try {
            setQuantite(Double.parseDouble(quantite));
        } catch (Exception e) {
            throw new Exception("La quantité doit être un nombre");
        }
    }
    public void setPrixUnitaire(String prixUnitaire) throws Exception {
        if (prixUnitaire.equals(null) || prixUnitaire.equals("")) {
            throw new Exception("Le prix unitaire ne peut pas être vide");
        }
        try {
            setPrixUnitaire(Double.parseDouble(prixUnitaire));
        } catch (Exception e) {
            throw new Exception("Le prix unitaire doit être un nombre");
        }
    }
    public List<Entree> getListEntree(String date, String article, String magasin) throws Exception {
        String condition = "date_entree <= '" + date + "' and id_article = '" + article + "' and id_magasin = '" + magasin + "'";
        return findWhere(null, condition);
    }

    public Double somme(List<Entree> list) {
        Double somme = 0.0;
        for (Entree entree : list) {
            somme += entree.getQuantite();
        }
        return somme;
    }
}
