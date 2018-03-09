package com.article.services;

import com.article.dao.ArticleDAO;
import com.article.entity.Article;
import com.article.enumerations.ResponseMessageStatus;
import com.article.dto.ArticleDTO;
import com.article.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class ArticleService implements ArticleServiceInterface {

    private ArticleDAO articleDAO;
    private ArticleRepository articleRepository;
    private MessageSource messageSource;

    @Autowired
    public ArticleService(ArticleDAO articleDAO, ArticleRepository articleRepository, MessageSource messageSource) {
        this.articleDAO = articleDAO;
        this.articleRepository = articleRepository;
        this.messageSource = messageSource;
    }



    @Override
    public ArticleDTO getAllArticleList(String locale) {
        try {
            List<Article> articles = new ArrayList<>();
            articleRepository.findAll()
                    .forEach(articles::add);
            return new ArticleDTO(true, messageSource.getMessage("success.ArticleDAO-getAllArticleList",null, new Locale(locale)), ResponseMessageStatus.SUCCESS, articles);
        } catch (Throwable throwable) {
            return new ArticleDTO(false,messageSource.getMessage("error.ArticleDAO-getAllArticleList",null, new Locale(locale)), ResponseMessageStatus.ERROR);
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
