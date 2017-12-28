package com.article.services;

import com.article.entity.Article;

import java.util.List;

public interface ArticleServiceInterface {
    List<Article> getAllArticleList();
    Article getArticle(long id);
    boolean addArticle(Article article);
    boolean articleExist(Article article);
    boolean updateArticle(Article article, long id);
    boolean deleteArticle(long id);
}
