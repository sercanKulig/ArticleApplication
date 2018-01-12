package com.article.repository;

import com.article.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryRedisImpl implements UserRepositoryRedis{


    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public UserRepositoryRedisImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(User user) {
        hashOperations.put("User", user.getUserId(), user);
    }

    @Override
    public List<User> findAll() {
        Map<String, User> users = hashOperations.entries("User");
        List<User> user = new LinkedList<>();
        user.addAll(users.values());
        return user;
    }

    @Override
    public User findById(int id) {
        return (User) hashOperations.get("User", id);
    }

    @Override
    public void update(User user) {
        save(user);
    }

    @Override
    public void delete(int id) {
        hashOperations.delete("User", id);
    }
}
