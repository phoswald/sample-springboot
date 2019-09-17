package com.github.phoswald.sample.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

@SpringBootApplication
@EnableJpaRepositories
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /*
     * Spring Boot will not search for or use a META-INF/persistence.xml by default.
     * If you prefer to use a traditional persistence.xml, you need to define your own @Bean
     * of type LocalEntityManagerFactoryBean (with an ID of ‘entityManagerFactory’) and
     * set the persistence unit name there.
     */
    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
        factoryBean.setPersistenceUnitName("taskDS");
        return factoryBean;
    }
}
