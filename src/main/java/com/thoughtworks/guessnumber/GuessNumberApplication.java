package com.thoughtworks.guessnumber;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.thoughtworks.guessnumber.mapper")
public class GuessNumberApplication {

    public static void main(String[] args) {

        SpringApplication.run(GuessNumberApplication.class, args);

    }
}
