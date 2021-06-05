package com.spring.kata;


import com.spring.kata.repository.Person;
import com.spring.kata.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
//        System.setProperty("server.servlet.context-path", "/app-high");
//        System.setProperty("server.servlet.path", "/app-high");
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PersonRepository repository) {
        return strings -> {
            repository.save(new Person("Daci", "Timisoara"));
            repository.save(new Person("Aranud", "Timisoara"));
            repository.save(new Person("Rony", "Timisoara"));
        };
    }

}
