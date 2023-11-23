package com.stock.stock.mapping;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import annotation.Column;
import annotation.PrimaryKey;
import annotation.Table;
import dao.BddObject;

@Table
public class Sortie extends BddObject<Sortie>{
    @PrimaryKey(length = 7, prefix = "SRT", sequence = "seq_sortie")
    @Column String id;
    @Column(name = "date_sortie") Date dateSortie;
    @Column Double quantite;
    @Column(name = "id_magasin") String idMagasin;
    @Column(name = "id_article") String idArticle;
    @Column Integer etat;

    public String getId() {
        return id;
    }
    public void setId(String id) throws Exception {
        if (id.equals(null) || id.equals("")) {
            throw new Exception("L'id ne peut pas être vide");
        }
        this.id = id;
    }
    public Date getDateSortie() {
        return dateSortie;
    }
    public void setDateSortie(Date dateSortie) throws Exception{
        this.dateSortie = dateSortie;
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
    public void setDateSortie(String datesortie) throws Exception{
        if (datesortie.equals(null) || datesortie.equals("")) {
            throw new Exception("La date de sortie ne peut pas être vide");
        }
        setDateSortie(Date.valueOf(datesortie));
    }

    public Integer getEtat() {
        return etat;
    }
    public void setEtat(Integer etat) {
        this.etat = etat;
    }
    public Validation getValidation(Connection con) throws Exception {
        Validation validation = new Validation();
        validation.setIdSortie(getId());
        return validation.findWhere(con, "id_sortie like '"+validation.getIdSortie()+"'").get(0);
    }

    public void checkDateUterieur(Connection con, Date date) throws Exception{
        Article article = new Article();
        article.setId(getIdArticle());
        Sortie dernierSortie = article.getDernierSortieValider(con, idMagasin);
        if (dernierSortie.getDateSortie().after(date)) {
            throw new Exception("La date de sortie doit être supérieur à la date du dernier sortie valider.");
        }
    }

    /*
     * Mijery raha mbola ampy anaovana sortie ve le quantite anle article ao
     * Mijery ny stock restant am date de validation farany
     *
     */
    public void estSuffisant(Connection con) {
        String query = "select ";
    }
    public List<Sortie> getListSortie(String date, String idArticle, String idMagasin) throws Exception {
        String condition = "date_entree <= '" + date + "' and id_article = '" + idArticle + "' and id_magasin = '" + idMagasin + "'";
        return findWhere(null, condition);
    }

    public Double somme(List<Sortie> list) {
        Double somme = 0.0;
        for (Sortie sortie : list) {
            somme += sortie.getQuantite();
        }
        return somme;
    }

    public List<Sortie> AllSortiesInvalider() throws Exception {
        Sortie sortie = new Sortie();
        String query = "select * from sortie where etat < 20";
        return sortie.executeQuery(null, query, sortie);
    }
}
