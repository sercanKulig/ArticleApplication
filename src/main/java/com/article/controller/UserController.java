package com.article.controller;

import com.article.entity.UserM;
import com.article.repository.UserRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@EnableCircuitBreaker
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {


/*    private UserService userService;

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
    }*/

    private UserRepositoryMongo repositoryMongo;

    @Autowired
    public UserController(UserRepositoryMongo repositoryMongo) {
        this.repositoryMongo = repositoryMongo;
    }

    @GetMapping("/users")
    public List<UserM> getUsers() {
        return repositoryMongo.findAll();
    }

    @GetMapping("/deleteUsers")
    public void deleteUsers() {
        repositoryMongo.deleteAll();
    }

    @RequestMapping("/user/{id}")
    public UserM getUserM(@PathVariable int id){
        return repositoryMongo.findByUserId(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteUser/{id}")
    public void deleteUserM(@PathVariable int id){
        repositoryMongo.deleteByUserId(id);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/saveUser")
    public List<UserM> saveUserM(@RequestBody UserM userM){
        repositoryMongo.save(userM);
        return repositoryMongo.findAll();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateUser")
    public List<UserM> updateUserM(@RequestBody UserM userM){
        repositoryMongo.save(userM);
        return repositoryMongo.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/checkUser")
    public boolean checkUser(@RequestBody UserM userM) {
        return repositoryMongo.existsByEmailAndPassword(userM.getEmail(),userM.getPassword());
    }

}
