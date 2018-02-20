package com.article.services;

import com.article.dao.ArticleDAO;
import com.article.entity.Article;
import com.article.enumerations.ResponseMessageStatus;
import com.article.dto.ArticleDTO;
import com.article.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService implements ArticleServiceInterface {

    private ArticleDAO articleDAO;
    private ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, ArticleDAO articleDAO) {
        this.articleRepository = articleRepository;
        this.articleDAO = articleDAO;
    }


    @Override
    public ArticleDTO getAllArticleList() {
        try {
            List<Article> articles = new ArrayList<>();
            articleRepository.findAll()
                    .forEach(articles::add);
            return new ArticleDTO(true, "ArticleDAO - getAllArticleList", ResponseMessageStatus.SUCCESS, articles);
        } catch (Throwable throwable) {
            return new ArticleDTO(false,"ArticleDAO - getAllArticleList", ResponseMessageStatus.ERROR);
        }

//        return articleDAO.getAllArticleList();
    }

    @Override
    public ArticleDTO getArticle(long id) {
        return articleDAO.getArticle(id);
    }

    @Override
    public synchronized ArticleDTO addArticle(Article article) {
        ArticleDTO articleDTO = articleDAO.articleExists(article);
        if (articleDTO.getStatus()) {
            return articleDTO;
        } else {
            return articleDAO.addArticle(article);
        }
    }

    @Override
    public ArticleDTO articleExist(Article article){
        try {
            return articleRepository.findOne(article.getArticleId()) != null ?
                    new ArticleDTO(true, "ArticleDAO - getAllArticleList", ResponseMessageStatus.SUCCESS) :
                    new ArticleDTO(false,"ArticleDAO - getAllArticleList", ResponseMessageStatus.ERROR);
        } catch (Throwable throwable) {
            return new ArticleDTO(false,"ArticleDAO - getAllArticleList", ResponseMessageStatus.ERROR);
        }
    }

    @Override
    public ArticleDTO updateArticle(Article article, long id) {
        return articleDAO.updateArticle(article, id);
    }

    @Override
    public ArticleDTO deleteArticle(long id) {
        return articleDAO.deleteArticle(id);
    }
}
