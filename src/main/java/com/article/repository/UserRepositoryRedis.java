package com.article.repository;

import com.article.entity.User;

import java.util.List;

public interface UserRepositoryRedis {
    void save(User user);
    List<User> findAll();
    User findById(int id);
    void update(User user);
    void delete(int id);
}
