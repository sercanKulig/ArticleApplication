package com.article.services;

import com.article.dao.ArticleDAO;
import com.article.entity.Article;
import com.article.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService implements ArticleServiceInterface {

    @Autowired
    ArticleDAO articleDAO;

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public List<Article> getAllArticleList() {
        List<Article> articles = new ArrayList<>();
        articleRepository.findAll()
                .forEach(articles::add);
        return articles;
//        return articleDAO.getAllArticleList();
    }

    @Override
    public Article getArticle(long id) {
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
    public boolean articleExist(Article article){
        return articleRepository.findOne(article.getArticleId()) != null;
    }

    @Override
    public boolean updateArticle(Article article, long id) {
        return articleDAO.updateArticle(article, id);
    }

    @Override
    public boolean deleteArticle(long id) {
        return articleDAO.deleteArticle(id);
    }
}
