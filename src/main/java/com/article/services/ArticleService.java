package com.article.services;

import com.article.dao.ArticleDAO;
import com.article.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService implements ArticleServiceInterface {

    @Autowired
    ArticleDAO articleDAO;

    @Override
    public List<Article> getAllArticleList() {
        return articleDAO.getAllArticleList();
    }

    @Override
    public Article getArticle(int id) {
        return articleDAO.getArticle(id);
    }

    @Override
    public synchronized boolean addArticle(Article article) {
        if (articleDAO.articleExists(article)) {
            return false;
        } else {
            articleDAO.addArticle(article);
            return true;
        }
    }

    @Override
    public void updateArticle(Article article, int id) {
        articleDAO.updateArticle(article, id);
    }

    @Override
    public void deleteArticle(int id) {
        articleDAO.deleteArticle(id);
    }
}
