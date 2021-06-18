package com.huma;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author hudenian
 * @date 2021/6/8
 */
@EnableScheduling
@SpringBootApplication
@MapperScan("com.huma.mapper")
public class VueBackApplication {
    public static void main(String[] args) {
        SpringApplication.run(VueBackApplication.class, args);
    }

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskExecutor = new ThreadPoolTaskScheduler();
        taskExecutor.setPoolSize(10);
        return taskExecutor;
    }
}
