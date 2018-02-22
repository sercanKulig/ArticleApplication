package com.article.repository;

import com.article.entity.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findOneByUsername(String userId);
    Optional<User> findOneByUserIdAndPassword(String userId, String password);
}
