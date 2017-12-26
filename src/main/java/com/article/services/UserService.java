package com.article.services;

import com.article.entity.Role;
import com.article.entity.User;

import java.util.List;

public interface UserService {

    boolean userExists(User user);
    List<User> getUserList();
    boolean addUser(User user);
    boolean addRole(Role role);
    User getUser(User user);
}
