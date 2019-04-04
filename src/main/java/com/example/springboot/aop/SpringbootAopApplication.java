package com.example.springboot.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories("com.example.springboot.aop.dao")
@EntityScan("com.example.springboot.aop.pojo")
@SpringBootApplication
public class SpringbootAopApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootAopApplication.class, args);
    }
}
