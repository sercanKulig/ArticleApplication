package com.article.dao;

import com.article.entity.Article;

import java.util.List;

public interface ArticleDAO {
    List<Article> getAllArticleList();
    Article getArticle(int id);
    void addArticle(Article article);
    void updateArticle(Article article, int id);
    void deleteArticle(int id);
    boolean articleExists(Article article);
}
