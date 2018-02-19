package com.article.repository;

import com.article.entity.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
