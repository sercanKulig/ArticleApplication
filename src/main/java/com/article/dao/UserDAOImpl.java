package com.article.dao;

import com.article.entity.Role;
import com.article.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Transactional
@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUserList() {
        return (List<User>) entityManager.createQuery(
                "FROM User as usrl ORDER BY usrl.id").getResultList();
    }

    @Override
    public User getUser(User user) {
        try {
            return (User) entityManager.createQuery(
                    "FROM User as usrl " +
                            "WHERE usrl.username = :username " +
                            "ORDER BY usrl.id")
                    .setParameter("username", user.getUsername())
                    .getSingleResult();
        } catch (NoResultException rte) {
            throw new NoResultException();
        }
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public boolean userExist(User user) {
        int count = entityManager.createQuery(
                "FROM User as usr " +
                        "WHERE usr.username = :username")
                .setParameter("username", user.getUsername())
                .getResultList().size();
        return count > 0;
    }

    @Override
    public boolean roleExist(Role role) {
        int count = entityManager.createQuery(
                "FROM Role as role " +
                        "WHERE role.role = :role")
                .setParameter("role", role.getRole())
                .getResultList().size();
        return count > 0;
    }

    @Override
    public boolean userExists(User user) {
        int count = entityManager.createQuery(
                "FROM User as usr " +
                        "WHERE usr.username = :username " +
                        "AND usr.password = :password " +
                        "AND usr.roleId = :roleId")
                .setParameter("username", user.getUsername())
                .setParameter("password", user.getPassword())
                .setParameter("roleId", user.getRoleId())
                .getResultList().size();
        return count > 0;
    }


    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }
}
