package com.faillog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FaillogApplication {

    public static void main(String[] args) {
        SpringApplication.run(FaillogApplication.class, args);
    }

}
