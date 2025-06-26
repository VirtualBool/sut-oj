package com.chen.sutoj;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;



@SpringBootApplication
@Slf4j
public class SutOjApplication {

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication.run(SutOjApplication.class, args);

    }

}
