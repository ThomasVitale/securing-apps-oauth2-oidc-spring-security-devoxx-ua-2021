package com.thomasvitale.edgeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableWebFluxSecurity
public class EdgeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EdgeServiceApplication.class, args);
    }

    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .authorizeExchange(authorize -> authorize.anyExchange().authenticated())
                .formLogin(Customizer.withDefaults())
                .build();
    }

    @Bean
    ReactiveUserDetailsService reactiveUserDetailsService() {
        var isabelle = User.withUsername("isabelle")
                .password("{bcrypt}$2a$10$d9GSkyKhcF.WElIJW6Cjsezr/C/1kZDXNEuts3Kht0LCF0Y8Svzne")
                .roles("employee")
                .build();
        return new MapReactiveUserDetailsService(isabelle);
    }

}

@RestController
class WelcomeController {

    @GetMapping("welcome")
    Mono<String> getWelcome() {
        return Mono.just("Welcome to Polar Bookshop!");
    }

}
