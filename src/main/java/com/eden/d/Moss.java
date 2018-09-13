package com.eden.d;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:moss.properties")
public class Moss {
    public static void main(String[] args) {
        SpringApplication.run(Moss.class, args);
    }
}
