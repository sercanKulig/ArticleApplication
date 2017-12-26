package com.article.services;

import com.article.dao.UserDAO;
import com.article.entity.Role;
import com.article.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    public List<User> getUserList() {
        return userDAO.getUserList();
    }

    public User getUser(User user) {
        return userDAO.getUser(user);
    }

    public synchronized boolean addUser(User user) {
        if(userDAO.userExist(user)) {
            return false;
        } else {
            userDAO.addUser(user);
            return true;
        }
    }

    public synchronized boolean addRole(Role role) {
        if(userDAO.roleExist(role)) {
            return false;
        } else {
            userDAO.addRole(role);
            return true;
        }
    }

    public synchronized boolean userExists(User user) {
        if(!user.getPassword().isEmpty() || user.getPassword() == null
                && !user.getUsername().isEmpty() || user.getUsername() == null
                && user.getRoleId() > 0) {
            return userDAO.userExists(user);
        }
        return false;
    }

}
