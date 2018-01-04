package com.article.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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

    @Column(name="date_creation")
    private Date dateCreation;

    public Article() {
    }

    public Article(long articleId, String title, String category, Date dateCreation) {
        this.articleId = articleId;
        this.title = title;
        this.category = category;
        this.dateCreation = dateCreation;
    }


    public Article(String title, String category, Date dateCreation) {
        this.title = title;
        this.category = category;
        this.dateCreation = dateCreation;
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
    public Date getDateCreation() {
        return dateCreation;
    }
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
}
