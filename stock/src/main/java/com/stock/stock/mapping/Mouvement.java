package com.stock.stock.mapping;

import annotation.Column;
import annotation.PrimaryKey;
import annotation.Table;
import dao.BddObject;

@Table(name = "mouvement")
public class Mouvement extends BddObject<Mouvement>{
    @PrimaryKey(length = 7, prefix = "MVT", sequence = "seq_mouvement")
    @Column String id;
    @Column(name = "id_sortie") String idSortie;
    @Column(name = "id_entree") String idEntree;
    @Column Double quantite;


    public String getId() {
        return id;
    }
    public void setId(String id) throws Exception {
        if (id.equals(null) || id.equals("")) {
            throw new Exception("L'id ne peut pas être vide");
        }
        this.id = id;
    }
    public String getIdSortie() {
        return idSortie;
    }
    public void setIdSortie(String idSortie) throws Exception {
        if (idSortie.equals(null) || idSortie.equals("")) {
            throw new Exception("idSortie ne peut pas être vide");
        }
        this.idSortie = idSortie;
    }
    public String getIdEntree() {
        return idEntree;
    }
    public void setIdEntree(String idEntree) throws Exception {
        if (idEntree.equals(null) || idEntree.equals("")) {
            throw new Exception("idEntree ne peut pas être vide");
        }
        this.idEntree = idEntree;
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

}
