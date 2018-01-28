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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication(
        scanBasePackages = {
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

public class MainController implements CommandLineRunner {

    private UserService userService;
    private ArticleService articleService;

    @Autowired
    public MainController(UserService userService, ArticleService articleService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainController.class, args);
    }

    public void run(String... strings) throws IOException {
        //save role
        Stream.of(
                new Role("ADMIN"),
                new Role("USER")
        ).forEach(
                role -> userService.addRole(role)
        );
        // save user
        Stream.of(
                new User("admin", "administrator", "admin", "123456", null, 1, 1)
        ).forEach(
                user -> userService.addUser(user)
        );
        // save article
        Stream.of(
                new Article("article", "articleCategory", new Date())
        ).forEach(
                article -> articleService.addArticle(article)
        );
    }

    public void filterExample() {
        List<String> names = Arrays.asList("Sercan", "Gizem", "Hande");

        for (String name : names) {
            if (!name.equals("Sercan")) {
                System.out.println(name);
            }
        }

        names.stream()
                .filter(name -> !name.equals("Sercan"))
                .forEach(System.out::println);
    }

    public void mapperExample() {
        List<String> names = Arrays.asList("Sercan", "Gizem", "Hande");

        names.stream()
                .filter(name -> !name.equals("Sercan"))
                .map(Userss::new)
                .forEach(System.out::println);

        List<Userss> userssList = names.stream()
                .filter(name -> !name.equals("Sercan"))
                .map(Userss::new)
                .collect(Collectors.toList());

        System.out.println(userssList);
    }

    public void mapToIntExample() {
        List<String> names = Arrays.asList("Sercan", "Gizem", "Hande");

        List<Userss> userssList = names.stream()
                .filter(name -> !name.equals("Sercan"))
                .map(Userss::new)
                .collect(Collectors.toList());

        int sum =userssList.stream()
                .mapToInt(Userss::getAge)
                .sum();

        System.out.println(sum);
    }

    public void flatMapExample() {
        List<Userss> users = Stream.of(
                new Userss("Sercan", 30, Arrays.asList("1","2")),
                new Userss("Gizem", 30, Arrays.asList("3","4")),
                new Userss("Hande", 25, Arrays.asList("5","6"))
        ).collect(Collectors.toList());

        Optional<String> usr = users.stream()
                .map(userss -> userss.getPhoneNumbers().stream())
                .flatMap(stringStream -> stringStream.filter(phoneNo -> phoneNo.equals("20")))
                .findAny();

                usr.ifPresent(System.out::println);
    }

    public void fileLineRead() throws IOException {
        List<String> stringList = Files.lines(Paths.get("D:/asd.txt"))
                .filter(line -> line.contains("Gizem"))
                .map(line -> line.replaceAll(" ",""))
                .collect(Collectors.toList());
        System.out.println(stringList);

    }

    static class Userss {
        private String name;
        private Integer age = 2;
        private List<String> phoneNumbers;

        public Userss(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public Userss(String name, Integer age, List<String> phoneNumbers) {
            this.name = name;
            this.age = age;
            this.phoneNumbers = phoneNumbers;
        }

        public Userss(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getPhoneNumbers() {
            return phoneNumbers;
        }

        public void setPhoneNumbers(List<String> phoneNumbers) {
            this.phoneNumbers = phoneNumbers;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

}