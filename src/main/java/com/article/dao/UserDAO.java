package com.article.dao;

import com.article.entity.user.User;

import java.util.List;

public interface UserDAO {
    List<User> getUserList();
    void addUser(User user);
    boolean userExist(User user);
    boolean userExists(User user);
    User getUser(User user);
}
