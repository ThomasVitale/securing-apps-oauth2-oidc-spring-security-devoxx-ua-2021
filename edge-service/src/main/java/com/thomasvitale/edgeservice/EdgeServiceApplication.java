package com.thomasvitale.edgeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class EdgeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EdgeServiceApplication.class, args);
    }

}

@RestController
class WelcomeController {

    @GetMapping("welcome")
    Mono<String> getWelcome() {
        return Mono.just("Welcome to Polar Bookshop!");
    }

}
