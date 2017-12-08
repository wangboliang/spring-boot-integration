package com.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProviderApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication application = new SpringApplication(ProviderApplication.class);
        application.run(args);
    }

}

