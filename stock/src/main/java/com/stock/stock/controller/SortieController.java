package com.stock.stock.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.stock.formulaire.SortieFormulaire;
import com.stock.stock.mapping.Sortie;

import dao.DbConnection;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/sorties")
public class SortieController {
    @GetMapping
    public List<Sortie> all() throws Exception {
        return new Sortie().findAll(null);
    }

    @GetMapping("/invalidate")
    public List<Sortie> allInvalidate() throws Exception {
        return new Sortie().AllSortiesInvalider();
    }

    @PostMapping("/add")
    public Sortie add(@RequestBody SortieFormulaire sortieFormulaire){
        Sortie sortie = new Sortie();
        System.out.println("huhu"+sortieFormulaire.getDateSortie());
        Connection con = null;
        try {
            con = new DbConnection().connect();
            sortie.setIdArticle(sortieFormulaire.getIdArticle());
            sortie.setQuantite(sortieFormulaire.getQuantite());
            sortie.setIdMagasin(sortieFormulaire.getIdMagasin());
            sortie.setEtat(sortieFormulaire.getEtat());
            sortie.setDateSortie(sortieFormulaire.getDateSortie());
            sortie.checkDateUterieur(con, sortie.getDateSortie());
            con.setAutoCommit(false);
            sortie.save(con);
            con.commit();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return sortie;
    }
}
