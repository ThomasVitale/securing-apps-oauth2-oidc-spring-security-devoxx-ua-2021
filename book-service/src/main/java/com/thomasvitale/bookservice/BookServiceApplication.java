package com.thomasvitale.bookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@SpringBootApplication
public class BookServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookServiceApplication.class, args);
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
