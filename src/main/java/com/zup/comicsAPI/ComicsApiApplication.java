package com.zup.comicsAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ComicsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComicsApiApplication.class, args);
    }

}
