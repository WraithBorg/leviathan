package com.io.hydralisk;

import com.io.hydralisk.component.sftp.SsFtpInstance;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;


@SpringBootApplication
@MapperScan("com.io.hydralisk.mapper")
public class HydraliskApplication {
    @Resource
    private SsFtpInstance ssFtpInstance;
    public static void main(String[] args) {
        SpringApplication.run(HydraliskApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        ssFtpInstance.initFtpInstance();
        return args -> {
            System.out.println("Let's inspect the beans provided by Spring Boot:");
        };
    }

}
