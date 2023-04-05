package com.mack.client;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@MapperScan("com.mack.client.mapper")
public class ApplicationClient9001 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationClient9001.class, args);
    }
}
