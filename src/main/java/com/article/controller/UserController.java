package com.article.controller;

import com.article.entity.user.User;
import com.article.services.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Return Users", response = User.class)
    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public List<User> getUsers() {
        return userService.getUserList();
    }

    @ApiOperation(value = "Return User", response = User.class)
    @RequestMapping(method = RequestMethod.POST, value = "/user")
    @HystrixCommand(fallbackMethod = "emptyUserReturn")
    public User getUser(@RequestBody User user) {
        return userService.getUser(user);
    }

    @ApiOperation(value = "Add User")
    @RequestMapping(method = RequestMethod.POST, value = "/addUser")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    public User emptyUserReturn(User user) {
        return user;
    }

    @RequestMapping("/helloUser")
    public String helloUser() {
        return "Hello Worlds";
    }

}
