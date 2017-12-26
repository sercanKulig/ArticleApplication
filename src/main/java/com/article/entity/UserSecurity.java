package com.article.entity;

import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Scope(value = "singleton")
@Table(name = "user_security")
public class UserSecurity implements Serializable {


    private Date sessionStart;
    private Date sessionEnd;
    private int userId;
    private int userSecurityId;
    private User user;


    public UserSecurity() {
    }


    @Id
    @Column(name = "user_security_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getUserSecurityId() {
        return userSecurityId;
    }

    public void setUserSecurityId(int userSecurityId) {
        this.userSecurityId = userSecurityId;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "session_start")
    public Date getSessionStart() {
        return sessionStart;
    }

    public void setSessionStart(Date sessionStart) {
        this.sessionStart = sessionStart;
    }

    @Column(name = "session_end")
    public Date getSessionEnd() {
        return sessionEnd;
    }

    public void setSessionEnd(Date sessionEnd) {
        this.sessionEnd = sessionEnd;
    }

    @OneToOne(mappedBy = "userSecurity")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
