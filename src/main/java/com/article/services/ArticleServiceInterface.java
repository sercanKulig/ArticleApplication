package com.article.services;

import com.article.entity.Article;
import com.article.dto.ArticleDTO;

public interface ArticleServiceInterface {
    ArticleDTO getAllArticleList();
    ArticleDTO getArticle(long id);
    ArticleDTO addArticle(Article article);
    ArticleDTO articleExist(Article article);
    ArticleDTO updateArticle(Article article, long id);
    ArticleDTO deleteArticle(long id);
}
