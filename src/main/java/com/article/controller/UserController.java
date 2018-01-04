package com.article.controller;

import com.article.entity.User;
import com.article.services.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@EnableCircuitBreaker
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {


    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

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
