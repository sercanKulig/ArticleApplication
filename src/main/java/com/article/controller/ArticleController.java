package com.article.controller;

import com.article.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.article.services.ArticleServiceInterface;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleServiceInterface articleService;

    @RequestMapping("/article")
    public List<Article> getArticles() {
        return articleService.getAllArticleList();
    }

/*    @RequestMapping("/articles/{id}")
    public Article getArticle(@PathVariable String id){
        return articleService.getArticle(id);
    }*/

    @RequestMapping(method = RequestMethod.POST, value = "/article/articleSingle")
    public Article getArticle(@RequestBody Article article){
        Article article1 = articleService.getArticle(article.getArticleId());
        return article1;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/article")
    public void addArticle(@RequestBody Article article) {
        articleService.addArticle(article);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/article/articleUpdate/{id}")
    public void updateArticle(@RequestBody Article article, @PathVariable int id) {
        articleService.updateArticle(article, id);
    }

//    @RequestMapping(method = RequestMethod.DELETE, value = "/articleDelete/{id}")
//    public void deleteArticle(@PathVariable String id) {
//        articleService.deleteArticle(id);
//    }

    @RequestMapping(method = RequestMethod.POST, value = "/article/articleDelete")
    public void deleteArticle(@RequestBody Article article) {
        articleService.deleteArticle(article.getArticleId());
    }
}
