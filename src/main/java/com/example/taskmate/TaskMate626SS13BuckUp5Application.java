package com.example.taskmate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.taskmate.repository")
public class TaskMate626SS13BuckUp5Application {

    public static void main(String[] args) {
        SpringApplication.run(TaskMate626SS13BuckUp5Application.class, args);
    }

}