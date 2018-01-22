package com.article.repository;

import com.article.entity.Article;
import com.article.entity.UserM;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepositoryMongo extends MongoRepository<UserM, Integer> {
    UserM findByUserId(int id);
    void deleteByUserId(int id);
    boolean existsByEmailAndPassword(String email, String password);
}
