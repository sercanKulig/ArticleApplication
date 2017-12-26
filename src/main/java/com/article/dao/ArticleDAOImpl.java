package com.article.dao;

import com.article.entity.Article;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository("articleDAO")
public class ArticleDAOImpl implements ArticleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Article> getAllArticleList() {
        String hql = "FROM Article as atcl ORDER BY atcl.articleId";
        return (List<Article>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public Article getArticle(int id) {
        return entityManager.find(Article.class, id);
    }

    @Override
    public void addArticle(Article article) {
        entityManager.persist(article);
    }

    @Override
    public void updateArticle(Article article, int id) {
        Article artcl = getArticle(article.getArticleId());
        artcl.setTitle(article.getTitle());
        artcl.setCategory(article.getCategory());
        entityManager.flush();
    }

    @Override
    public void deleteArticle(int id) {
        entityManager.remove(getArticle(id));
    }

    @Override
    public boolean articleExists(Article article) {
        String hql = "FROM Article as atcl WHERE atcl.title = ? and atcl.category = ?";
        int count = entityManager.createQuery(hql).setParameter(1, article.getTitle())
                .setParameter(2, article.getCategory()).getResultList().size();
        return count > 0 ? true : false;
    }
}
