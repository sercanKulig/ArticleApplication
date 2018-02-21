package com.article.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private int userId;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private int roleId;
    private int active;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Role role;
    private UserSecurity userSecurity;

    public User(String name, String surname, String username, String password, String email, int active, int roleId) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.active = active;
        this.roleId = roleId;
    }

    public User(int userId, String name, String surname, String username, String password, String email, int roleId, int active) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roleId = roleId;
        this.active = active;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(UserSecurity userSecurity) {
        this.userSecurity = userSecurity;
    }

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getUserId() {
        return userId;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_security_id")
    @JsonIgnore
    public UserSecurity getUserSecurity() {
        return userSecurity;
    }

    @Column(name = "role_id")
    public int getRoleId() {
        return roleId;
    }

    @JsonIgnore
    @Transient
    public Role getRole() {
        return role;
    }
}
