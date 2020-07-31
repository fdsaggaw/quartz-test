package com.su;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.su.dao")
public class QuartzDynamicApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuartzDynamicApplication.class,args);
    }
}
