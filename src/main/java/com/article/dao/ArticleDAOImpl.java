package com.article.dao;

import com.article.entity.Article;
import com.article.enumerations.ResponseMessageStatus;
import com.article.dto.ArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;

@Transactional
@Repository("articleDAO")
public class ArticleDAOImpl implements ArticleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MessageSource messageSource;

    @Override
    public ArticleDTO getAllArticleList(String locale) {
        try{
            String hql = "FROM Article as atcl ORDER BY atcl.articleId";
            return new ArticleDTO(true, messageSource.getMessage("success.ArticleDAO-getAllArticleList",null, new Locale(locale)), ResponseMessageStatus.SUCCESS, (List<Article>) entityManager.createQuery(hql).getResultList());
        } catch (Throwable throwable) {
            return new ArticleDTO(false,messageSource.getMessage("success.ArticleDAO-getAllArticleList",null, new Locale(locale)), ResponseMessageStatus.ERROR);
        }



    }

    @Override
    public ArticleDTO getArticle(long id) {
        try{
            return new ArticleDTO(true, "ArticleDAO - getArticle", ResponseMessageStatus.SUCCESS, entityManager.find(Article.class, id));
        } catch (Throwable throwable) {
            return new ArticleDTO(false,"ArticleDAO - getArticle", ResponseMessageStatus.ERROR);
        }
    }

    @Override
    public ArticleDTO addArticle(Article article) {
        try{
            entityManager.persist(article);
            return new ArticleDTO(true, "ArticleDAO - addArticle", ResponseMessageStatus.SUCCESS);
        } catch (Throwable throwable) {
            return new ArticleDTO(false,"ArticleDAO - addArticle", ResponseMessageStatus.ERROR);
        }
    }

    @Override
    public ArticleDTO updateArticle(Article article, long id) {
        try{
            Article artcl = entityManager.find(Article.class, article.getArticleId());
            artcl.setTitle(article.getTitle());
            artcl.setCategory(article.getCategory());
            entityManager.flush();
            return new ArticleDTO(true,"ArticleDAO - updateArticle", ResponseMessageStatus.SUCCESS);
        }catch (Throwable throwable) {
            return new ArticleDTO(false,"ArticleDAO - updateArticle", ResponseMessageStatus.ERROR);
        }
    }

    @Override
    public ArticleDTO deleteArticle(long id) {
        try{
            entityManager.remove(getArticle(id).getArticle());
            return new ArticleDTO(true,"ArticleDAO - updateArticle", ResponseMessageStatus.SUCCESS);
        }catch (Throwable throwable) {
            return new ArticleDTO(false,"ArticleDAO - updateArticle", ResponseMessageStatus.ERROR);
        }
    }

    @Override
    public ArticleDTO articleExists(Article article) {
        try{
            String hql = "FROM Article as atcl WHERE atcl.title = ? and atcl.category = ?";
            int count = entityManager.createQuery(hql).setParameter(1, article.getTitle())
                    .setParameter(2, article.getCategory()).getResultList().size();
            return count > 0 ? new ArticleDTO(true,"ArticleDAO - articleExists", ResponseMessageStatus.SUCCESS) : new ArticleDTO(false,"ArticleDAO - articleExists", ResponseMessageStatus.ERROR);
        }catch (Throwable throwable) {
            return new ArticleDTO(false,"ArticleDAO - articleExists", ResponseMessageStatus.ERROR);
        }
    }
}
