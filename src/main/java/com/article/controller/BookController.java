package com.article.controller;

import com.article.services.BookGraphQLService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest")
@RestController
public class BookController {

    @Autowired
    BookGraphQLService bookGraphQLService;

    @PostMapping("/books")
    public ResponseEntity<Object> getAllBooks(@RequestBody String query) {
        ExecutionResult execute = bookGraphQLService.getGraphQL().execute(query);
        return new ResponseEntity<>(execute, HttpStatus.OK);
    }
}
