package com.article.controller;

import com.article.entity.Article;
import com.article.dto.ArticleDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.article.services.ArticleServiceInterface;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:4200")
public class ArticleController {

    private ArticleServiceInterface articleService;

    @Autowired
    public ArticleController(ArticleServiceInterface articleServiceInterface) {
        this.articleService = articleServiceInterface;
    }

    @ApiOperation(value = "Returns Articles", response = Article.class)
    @RequestMapping(method = RequestMethod.GET, value = "/articles")
    public ArticleDTO getArticles() {
        return articleService.getAllArticleList();
    }

    @ApiOperation(value = "Returns Article", response = Article.class)
    @RequestMapping(method = RequestMethod.GET, value = "/article/{id}")
    public ArticleDTO getArticle(@PathVariable long id){
        return articleService.getArticle(id);
    }

/*    @RequestMapping(method = RequestMethod.POST, value = "/article")
    public Article getArticle(@RequestBody Article article){
        return articleService.getArticle(article.getArticleId());
    }*/

    @ApiOperation(value = "Add Article")
    @RequestMapping(method = RequestMethod.POST, value = "/addArticle")
    public ArticleDTO addArticle(@RequestBody Article article) {
        return articleService.addArticle(article);
    }

    @ApiOperation(value = "Update Article")
    @RequestMapping(method = RequestMethod.PUT, value = "/articleUpdate/{id}")
    public void updateArticle(@RequestBody Article article, @PathVariable long id) {
        articleService.updateArticle(article, id);
    }

//    @RequestMapping(method = RequestMethod.DELETE, value = "/articleDelete/{id}")
//    public void deleteArticle(@PathVariable String id) {
//        articleService.deleteArticle(id);
//    }

    @ApiOperation(value = "Delete Article")
    @RequestMapping(method = RequestMethod.POST, value = "/articleDelete")
    public void deleteArticle(@RequestBody Article article) {
        articleService.deleteArticle(article.getArticleId());
    }
}
