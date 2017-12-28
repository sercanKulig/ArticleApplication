package com.article.dao;

import com.article.entity.Article;

import java.util.List;

public interface ArticleDAO {
    List<Article> getAllArticleList();
    Article getArticle(long id);
    void addArticle(Article article);
    boolean updateArticle(Article article, long id);
    boolean deleteArticle(long id);
    boolean articleExists(Article article);
}
