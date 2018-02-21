package com.article.services;

import com.article.entity.user.User;

import java.util.List;

public interface UserService {

    boolean userExists(User user);
    List<User> getUserList();
    boolean addUser(User user);
    User getUser(User user);
}
