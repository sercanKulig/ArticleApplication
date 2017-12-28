package com.article.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="article")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="article_id")
    private long articleId;

    @Column(name="title")
    private String title;

    @Column(name="category")
    private String category;

    public Article() {
    }

    public Article(long articleId, String title, String category) {
        this.articleId = articleId;
        this.title = title;
        this.category = category;
    }


    public Article(String title, String category) {
        this.title = title;
        this.category = category;
    }

    public long getArticleId() {
        return articleId;
    }
    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}
