package com.article.services;

import com.article.entity.Article;

import java.util.List;

public interface ArticleServiceInterface {
    List<Article> getAllArticleList();
    Article getArticle(int id);
    boolean addArticle(Article article);
    void updateArticle(Article article, int id);
    void deleteArticle(int id);
}
