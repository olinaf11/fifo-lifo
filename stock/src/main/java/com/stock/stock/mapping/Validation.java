package com.stock.stock.mapping;

import java.sql.Date;

import annotation.Column;
import annotation.PrimaryKey;
import annotation.Table;
import dao.BddObject;

@Table
public class Validation extends BddObject<Validation>{
    @PrimaryKey(length = 7, prefix = "VAL", sequence = "seq_validation")
    String id;
    @Column(name = "id_sortie")
    String idSortie;
    @Column
    Date date;

    public String getId() {
        return id;
    }
    public void setId(String id) throws Exception{
        if (id.equals(null) || id.equals("")) {
            throw new Exception("L'id ne doit pas être vide.");
        }
        this.id = id;
    }
    public String getIdSortie() {
        return idSortie;
    }
    public void setIdSortie(String idSortie) throws Exception{
        if (idSortie.equals(null) || idSortie.equals("")) {
            throw new Exception("L'id sortie ne doit pas être vide.");
        }
        this.idSortie = idSortie;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setDate(String date) throws Exception{
        if (date.equals(null) || date.equals("")) {
            throw new Exception("La date ne doit pas être vide");
        }
        setDate(Date.valueOf(date));
    }


}
