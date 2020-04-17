package com.codingforfun.eduaws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan({"com.codingforfun"})
public class AwsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AwsApplication.class, args);
    }
}
