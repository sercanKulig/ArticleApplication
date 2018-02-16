package com.article.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Scope(value = "singleton")
@Table(name = "user_security")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSecurity implements Serializable {


    private Date sessionStart;
    private Date sessionEnd;
    private int userId;
    private int userSecurityId;
    private User user;


    @Id
    @Column(name = "user_security_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getUserSecurityId() {
        return userSecurityId;
    }

    @Column(name = "session_start")
    public Date getSessionStart() {
        return sessionStart;
    }

    @Column(name = "session_end")
    public Date getSessionEnd() {
        return sessionEnd;
    }

    @OneToOne(mappedBy = "userSecurity")
    public User getUser() {
        return user;
    }
}
