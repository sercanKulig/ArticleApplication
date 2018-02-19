package com.article.model.dto;

import com.article.entity.Article;
import com.article.enumerations.ResponseMessageStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleDTO extends ResponseDTO {

    private List<Article> articles;
    private Article article;

    public ArticleDTO(Boolean status, String message, ResponseMessageStatus responseMessageStatus, List<Article> articles) {
        super(status, message, responseMessageStatus);
        this.articles = articles;
    }

    public ArticleDTO(Boolean status, String message, ResponseMessageStatus responseMessageStatus, Article article) {
        super(status, message, responseMessageStatus);
        this.article = article;
    }

    public ArticleDTO(Boolean status, String message, ResponseMessageStatus responseMessageStatus) {
        super(status, message, responseMessageStatus);
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
