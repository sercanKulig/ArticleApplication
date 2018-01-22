package com.article.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Document
@Table(name = "userM")
public class UserM {
    @Id
    private int userId;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private int roleId;
    private int active;

    public UserM() {
    }

    public UserM(int userId, String name, String surname, String username, String password, String email, int roleId, int active) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roleId = roleId;
        this.active = active;
    }

    public UserM(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
