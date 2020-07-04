package com.io.hydralisk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.io.hydralisk.mapper")
public class HydraliskApplication {

    public static void main(String[] args) {
        SpringApplication.run(HydraliskApplication.class, args);
    }

}
