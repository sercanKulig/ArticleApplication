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

@SpringBootApplication(
        scanBasePackages={
                "com.article.controller",
                "com.article.services",
                "com.article.dao"
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
    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

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
        articleService.addArticle(new Article("article","articleCategory"));
    }
}