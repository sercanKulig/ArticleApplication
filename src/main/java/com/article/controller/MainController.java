package com.article.controller;

import com.article.entity.Article;
import com.article.entity.Role;
import com.article.entity.User;
import com.article.entity.UserM;
import com.article.repository.UserRepositoryMongo;
import com.article.services.ArticleService;
import com.article.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Date;

@SpringBootApplication(
        scanBasePackages = {
                "com.article.controller",
                "com.article.services",
                "com.article.dao",
                "com.article.aspect",
                "com.article.schedular",
                "com.article.repository"
        }
)
@EntityScan(
        basePackages = {
                "com.article.entity"
        }
)
@EnableJpaRepositories(
        basePackages = {
                "com.article.repository"
        }
)

@EnableMongoRepositories(
        basePackages = {
                "com.article.repository"
        }
)

public class MainController implements CommandLineRunner {

    private UserService userService;
    private ArticleService articleService;
    private UserRepositoryMongo repositoryMongo;

    @Autowired
    public MainController(UserService userService, ArticleService articleService, UserRepositoryMongo repositoryMongo) {
        this.userService = userService;
        this.articleService = articleService;
        this.repositoryMongo = repositoryMongo;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainController.class, args);
    }

    public void run(String... strings) {
        //save role
        userService.addRole(new Role("ADMIN"));
        userService.addRole(new Role("USER"));
        // save user
        userService.addUser(new User("admin", "administrator", "admin", "123456", null, 1, 1));
        // save article
        articleService.addArticle(new Article("article", "articleCategory", new Date()));

        repositoryMongo.save(new UserM(1,"name1","surname1","username1","password1","email1",1,1));
        repositoryMongo.save(new UserM(2,"name2","surname2","username2","password2","email2",2,2));
    }

}