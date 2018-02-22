package com.article.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @Getter
    @Setter
    private String userId;
    @Getter
    @Setter
    private String password = "";
    @Getter
    @Setter
    private String company;
    @Getter
    @Setter
    private String firstName;
    @Getter
    @Setter
    private String lastName;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String email;

    @JsonIgnore
    @Getter
    @Setter
    private int securityProviderId;
    @JsonIgnore
    @Getter
    @Setter
    private int defaultCustomerId;

    @JsonIgnore
    @Getter
    @Setter
    private String phone;
    @JsonIgnore
    @Getter
    @Setter
    private String address1;
    @JsonIgnore
    @Getter
    @Setter
    private String address2;
    @JsonIgnore
    @Getter
    @Setter
    private String country;
    @JsonIgnore
    @Getter
    @Setter
    private String postal;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private Role role;

    //@JsonIgnore
    @JsonIgnore
    @Getter
    @Setter
    private boolean isActive;
    //@JsonIgnore
    @JsonIgnore
    @Getter
    @Setter
    private boolean isBlocked;
    @JsonIgnore
    @Getter
    @Setter
    private String secretQuestion;
    @JsonIgnore
    @Getter
    @Setter
    private String secretAnswer;
    @JsonIgnore
    @Getter
    @Setter
    private boolean enableBetaTesting;
    @JsonIgnore
    @Getter
    @Setter
    private boolean enableRenewal;

    public User() {
        this("new", "PASSWORD", Role.USER, "new", "new", "new", true, "", "", "", "", "", "", "", "", true, false);
    }

    public User(String userId, String password, String firstName, String lastName, String username) {
        this(userId, password, Role.USER, firstName, lastName, username, true, "", "", "", "", "", "", "", "", true, false);
    }

    public User(String userId, String password, Role role, String firstName, String lastName, String username) {
        this(userId, password, role, firstName, lastName, username, true, "", "", "", "", "", "", "", "", true, false);
    }

    public User(String userId, String password, Role role, String firstName, String lastName, boolean isActive, String username) {
        this(userId, password, role, firstName, lastName, username, isActive, "", "", "", "", "", "", "", "", true, false);
    }

    public User(String userId, String email, String password, Role role, String username, boolean isActive) {
        this.setUserId(userId);
        this.setEmail(email);
        this.setPassword(password);
        this.setRole(role);
        this.setUsername(username);
        this.setActive(isActive);
    }

    public User(String email, String password, Role role, String username, boolean isActive) {
        this.setEmail(email);
        this.setPassword(password);
        this.setRole(role);
        this.setUsername(username);
        this.setActive(isActive);
    }

    public User(String userId, String password, Role role, String firstName, String lastName, String username, boolean isActive,
                String company, String phone, String address1, String address2, String country, String postal,
                String secretQuestion, String secretAnswer, boolean enableRenewal, boolean enableBetaTesting) {
        this.setUserId(userId);
        this.setEmail(userId);
        this.setPassword(password);
        this.setRole(role);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setUsername(username);
        this.setActive(isActive);
        this.setCompany(company);
        this.setPhone(phone);
        this.setAddress1(address1);
        this.setAddress2(address2);
        this.setCountry(country);
        this.setPostal(postal);
        this.setSecretQuestion(secretQuestion);
        this.setSecretAnswer(secretAnswer);
        this.setEnableRenewal(enableRenewal);
        this.setEnableBetaTesting(enableBetaTesting);
    }

    public String getFullName() {
        return this.firstName + this.lastName;
    }
}
