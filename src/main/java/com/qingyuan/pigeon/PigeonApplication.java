package com.qingyuan.pigeon;


import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qingyuan.pigeon.mapper")
public class PigeonApplication {

    public static void main(String[] args) {
        SpringApplication.run(PigeonApplication.class,args);
    }

}
