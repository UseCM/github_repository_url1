package com.rd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
public class ApplicationCommon {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationCommon.class,args);
    }
}
