package com.article.controller;

import com.article.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.article.services.ArticleServiceInterface;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class ArticleController {

    @Autowired
    private ArticleServiceInterface articleService;

    @RequestMapping("/articles")
    public List<Article> getArticles() {
        return articleService.getAllArticleList();
    }

    @RequestMapping("/article/{id}")
    public Article getArticle(@PathVariable long id){
        return articleService.getArticle(id);
    }

/*    @RequestMapping(method = RequestMethod.POST, value = "/article")
    public Article getArticle(@RequestBody Article article){
        return articleService.getArticle(article.getArticleId());
    }*/

    @RequestMapping(method = RequestMethod.POST, value = "/addArticle")
    public void addArticle(@RequestBody Article article) {
        articleService.addArticle(article);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/articleUpdate/{id}")
    public void updateArticle(@RequestBody Article article, @PathVariable long id) {
        articleService.updateArticle(article, id);
    }

//    @RequestMapping(method = RequestMethod.DELETE, value = "/articleDelete/{id}")
//    public void deleteArticle(@PathVariable String id) {
//        articleService.deleteArticle(id);
//    }

    @RequestMapping(method = RequestMethod.POST, value = "/articleDelete")
    public void deleteArticle(@RequestBody Article article) {
        articleService.deleteArticle(article.getArticleId());
    }
}
