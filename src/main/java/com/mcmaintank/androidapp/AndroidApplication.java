package com.mcmaintank.androidapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.mcmaintank.androidapp.model","com.mcmaintank.androidapp.service","com.mcmaintank.androidapp.controller"})
@MapperScan(basePackages = {"com.mcmaintank.androidapp.mapper"})
@SpringBootApplication
public class AndroidApplication {

    public static void main(String[] args) {
        SpringApplication.run(AndroidApplication.class, args);
    }

}
