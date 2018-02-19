package com.article.dao;

import com.article.entity.Article;
import com.article.model.dto.ArticleDTO;

public interface ArticleDAO {
    ArticleDTO getAllArticleList();
    ArticleDTO getArticle(long id);
    ArticleDTO addArticle(Article article);
    ArticleDTO updateArticle(Article article, long id);
    ArticleDTO deleteArticle(long id);
    ArticleDTO articleExists(Article article);
}
