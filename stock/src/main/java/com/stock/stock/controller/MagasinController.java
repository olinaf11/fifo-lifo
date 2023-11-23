package com.stock.stock.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.stock.mapping.Magasin;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/magasins")
public class MagasinController {
    @GetMapping("/{id}")
    public Magasin getMagasin(@PathVariable String id) throws Exception {
        System.out.println("Id : "+id);
        Magasin magasin = new Magasin();
        return magasin.findById(null, id);
    }
    @GetMapping
    public List<Magasin> all() throws Exception {
        return new Magasin().findAll(null);
    }
}
