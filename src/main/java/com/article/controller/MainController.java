package com.article.controller;

import com.article.entity.Article;
import com.article.entity.Role;
import com.article.entity.User;
import com.article.services.ArticleService;
import com.article.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Date;

@SpringBootApplication(
        scanBasePackages={
                "com.article.controller",
                "com.article.services",
                "com.article.dao",
                "com.article.aspect",
                "com.article.schedular"
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

public class MainController  implements CommandLineRunner {

    private UserService userService;
    private ArticleService articleService;

    @Autowired
    public MainController(UserService userService, ArticleService articleService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainController.class,args);
    }

    public void run(String... strings) {
        //save role
        userService.addRole(new Role("ADMIN"));
        userService.addRole(new Role("USER"));
        // save user
        userService.addUser(new User("admin","administrator","admin","123456",null,1,1));
        // save article
        articleService.addArticle(new Article("article","articleCategory",new Date()));
    }

}