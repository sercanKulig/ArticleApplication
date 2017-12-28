package com.article.controller;

import com.article.entity.User;
import com.article.services.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableCircuitBreaker
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/users")
    public List<User> getUsers() {
        return userService.getUserList();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user")
    @HystrixCommand(fallbackMethod = "emptyUserReturn")
    public User getUser(@RequestBody User user) {
        User user1 = userService.getUser(user);
        return user1;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addUser")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    public User emptyUserReturn(User user) {
        return user;
    }

    @RequestMapping("/helloUser")
    public String helloUser() {
        return "Hello World";
    }

}
