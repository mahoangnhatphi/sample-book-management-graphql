package com.phi.bookmanagementclient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookManagementClientApplication implements CommandLineRunner {

    private GraphQLClient graphQLClient;

    public BookManagementClientApplication(GraphQLClient graphQLClient) {
        this.graphQLClient = graphQLClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookManagementClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var books = graphQLClient.getData();
        books.forEach(System.out::println);
    }
}
