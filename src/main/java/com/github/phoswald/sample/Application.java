package com.github.phoswald.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = "com.github.phoswald.sample")
@EntityScan("com.github.phoswald.sample")
@ConfigurationPropertiesScan("com.github.phoswald.sample")
public class Application {

    static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
