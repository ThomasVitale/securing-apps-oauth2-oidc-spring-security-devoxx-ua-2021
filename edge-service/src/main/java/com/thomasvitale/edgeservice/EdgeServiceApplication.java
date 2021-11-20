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

    record Welcome(String message){}

    @GetMapping("welcome")
    Mono<Welcome> getWelcome() {
        return Mono.just(new Welcome("Welcome to Polar Bookshop!"));
    }

}
