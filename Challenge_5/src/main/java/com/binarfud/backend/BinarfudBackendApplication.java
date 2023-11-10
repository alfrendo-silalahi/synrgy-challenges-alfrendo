package com.binarfud.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BinarfudBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BinarfudBackendApplication.class, args);
    }

}
