package com.article.services;

import com.article.entity.Book;
import com.article.repository.BookRepository;
import com.article.services.datafetchers.AllBooksDataFetcher;
import com.article.services.datafetchers.BooksDataFetcher;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;


@Service
public class BookGraphQLService {

    @Value("classpath:books.graphql")
    Resource bookResource;

    // for start up data
    @Autowired
    BookRepository bookRepository;

    private GraphQL graphQL;
    @Autowired
    private AllBooksDataFetcher allBooksDataFetcher;
    @Autowired
    private BooksDataFetcher bookDataFetcher;

    @PostConstruct
    private void loadSchema() throws IOException {
        loadDataIntoDB();
        // get the schema
        File schemaFile = bookResource.getFile();
        // parse schema
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring
                .newRuntimeWiring()
                .type("Query", typeWiring -> 
                    typeWiring.dataFetcher("allBooks", allBooksDataFetcher)
                    .dataFetcher("book", bookDataFetcher)
                )
                .build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }

    private void loadDataIntoDB() {
        Stream.of(
                new Book("123456","DenemeTitle1", "DenemePublisher1",new String[] {"DenemeYazar1","DenemeYazar2"}, "Nov 2017"),
                new Book("123457","DenemeTitle2", "DenemePublisher2",new String[] {"DenemeYazar3","DenemeYazar4"}, "Nov 2016"),
                new Book("123458","DenemeTitle3", "DenemePublisher3",new String[] {"DenemeYazar5","DenemeYazar6"}, "Nov 2013"),
                new Book("123459","DenemeTitle4", "DenemePublisher4",new String[] {"DenemeYazar7","DenemeYazar8"}, "Nov 2012")
        ).forEach(
                books -> bookRepository.save(books)
        );
    }
}
