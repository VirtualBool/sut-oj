package com.chen.sutoj;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@MapperScan("com.chen.sutoj.mapper")
@Slf4j
public class SutOjApplication {

    public static void main(String[] args)  {
        SpringApplication.run(SutOjApplication.class, args);

    }

}
