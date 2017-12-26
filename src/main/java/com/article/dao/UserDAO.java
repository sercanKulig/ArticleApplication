package com.article.dao;

import com.article.entity.Role;
import com.article.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> getUserList();
    void addUser(User user);
    boolean userExist(User user);
    boolean userExists(User user);
    User getUser(User user);
    boolean roleExist(Role role);
    void addRole(Role role);
}
