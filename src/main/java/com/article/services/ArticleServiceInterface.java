package com.article.services;

import com.article.entity.Article;
import com.article.dto.ArticleDTO;

import java.util.Locale;

public interface ArticleServiceInterface {
    ArticleDTO getAllArticleList(String locale);
    ArticleDTO getArticle(long id);
    ArticleDTO addArticle(Article article);
    ArticleDTO articleExist(Article article);
    ArticleDTO updateArticle(Article article, long id);
    ArticleDTO deleteArticle(long id);
}
