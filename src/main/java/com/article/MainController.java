package com.article;

import com.article.services.ArticleService;
import com.article.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
        //save role
//        userService.addRole(new Role("ADMIN"));
//        userService.addRole(new Role("USER"));
//        // save user
//        userService.addUser(new User("admin","administrator","admin","123456","admin@gmail.com",1,1));
//        // save article
//        articleService.addArticle(new Article("article","articleCategory",new Date()));
    }

}