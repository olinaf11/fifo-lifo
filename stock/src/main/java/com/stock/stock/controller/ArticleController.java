package com.stock.stock.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.stock.mapping.Article;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/articles")
public class ArticleController {
    @GetMapping("/{id}")
    public Article getArticle(@PathVariable String id) throws Exception {
        System.out.println("Id : "+id);
        Article article = new Article();
        return article.findById(null, id);
    }
    @GetMapping
    public List<Article> all() throws Exception {
        return new Article().findAll(null);
    }
}
