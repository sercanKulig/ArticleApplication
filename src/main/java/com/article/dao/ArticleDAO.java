package com.article.dao;

import com.article.entity.Article;
import com.article.dto.ArticleDTO;

public interface ArticleDAO {
    ArticleDTO getAllArticleList(String locale);
    ArticleDTO getArticle(long id);
    ArticleDTO addArticle(Article article);
    ArticleDTO updateArticle(Article article, long id);
    ArticleDTO deleteArticle(long id);
    ArticleDTO articleExists(Article article);
}
