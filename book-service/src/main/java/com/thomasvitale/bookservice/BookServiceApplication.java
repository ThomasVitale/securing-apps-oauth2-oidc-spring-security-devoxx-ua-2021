package com.thomasvitale.bookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@SpringBootApplication
@EnableWebSecurity
public class BookServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookServiceApplication.class, args);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests(authorize -> authorize.anyRequest().authenticated())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .build();
    }

}

record Book(String isbn, String title){}

@RestController
@RequestMapping("books")
class BookController {

    private static final Map<String,Book> books = new HashMap<>();

    static {
        var book1 = new Book("1234567891", "The Hobbit");
        var book2 = new Book("1234567892", "His Dark Materials");
        books.put(book1.isbn(), book1);
        books.put(book2.isbn(), book2);
    }

    @GetMapping
    Collection<Book> getBooks() {
        return books.values();
    }

    @GetMapping("{isbn}")
    Optional<Book> getBookIsbn(@PathVariable String isbn) {
        return Optional.of(books.get(isbn));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Book createBook(@RequestBody Book book) {
        books.put(book.isbn(), book);
        return book;
    }

}
