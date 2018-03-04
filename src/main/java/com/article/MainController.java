package com.article;

import com.article.entity.Article;
import com.article.entity.user.Role;
import com.article.entity.user.User;
import com.article.services.ArticleService;
import com.article.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
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

@EnableSwagger2
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
//        // save user
        userService.addUser(new User(1, "123456", Role.USER, "admin", "admin", "admin", "email", true, "", "", "", "", "", "", "", "", true, false));

//        // save article
        Stream.of(
            new Article("article_1","articleCategory",new Date()),
            new Article("article_2","articleCategory",new Date()),
            new Article("article_3","articleCategory",new Date()),
            new Article("article_4","articleCategory",new Date()),
            new Article("article_5","articleCategory",new Date()),
            new Article("article_6","articleCategory",new Date()),
            new Article("article_7","articleCategory",new Date()),
            new Article("article_8","articleCategory",new Date()),
            new Article("article_9","articleCategory",new Date()),
            new Article("article_10","articleCategory",new Date()),
            new Article("article_11","articleCategory",new Date()),
            new Article("article_12","articleCategory",new Date()),
            new Article("article_13","articleCategory",new Date()),
            new Article("article_14","articleCategory",new Date()),
            new Article("article_15","articleCategory",new Date()),
            new Article("article_16","articleCategory",new Date()),
            new Article("article_17","articleCategory",new Date()),
            new Article("article_18","articleCategory",new Date()),
            new Article("article_19","articleCategory",new Date()),
            new Article("article_20","articleCategory",new Date()),
            new Article("article_21","articleCategory",new Date())
        ).forEach(
                article -> articleService.addArticle(article)
        );
    }

}