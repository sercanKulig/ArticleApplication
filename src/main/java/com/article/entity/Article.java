package com.article.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="article")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public Article(String title, String category, Date dateCreation) {
        this.title = title;
        this.category = category;
        this.dateCreation = dateCreation;
    }
}
