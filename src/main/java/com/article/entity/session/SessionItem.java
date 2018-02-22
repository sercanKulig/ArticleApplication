package com.article.entity.session;

import lombok.Data;

import java.util.List;

@Data
public class SessionItem {
    private String  token;
    private Integer  userId;
    private String  firstName;
    private String  lastName;
    private String  email;
    private List<String> roles;
}
