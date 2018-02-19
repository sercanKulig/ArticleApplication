package com.article.services;

import com.article.entity.Article;
import com.article.model.dto.ArticleDTO;

import java.util.List;

public interface ArticleServiceInterface {
    ArticleDTO getAllArticleList();
    ArticleDTO getArticle(long id);
    ArticleDTO addArticle(Article article);
    ArticleDTO articleExist(Article article);
    ArticleDTO updateArticle(Article article, long id);
    ArticleDTO deleteArticle(long id);
}
