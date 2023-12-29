package com.example.oracleandroid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages = "com.example.oracleandroid")
public class OracleAndroidApplication {

    public static void main(String[] args) {
        SpringApplication.run(OracleAndroidApplication.class, args);
    }

}
