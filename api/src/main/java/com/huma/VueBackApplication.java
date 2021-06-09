package com.huma;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hudenian
 * @date 2021/6/8
 */
@SpringBootApplication
@MapperScan("com.huma.mapper")
public class VueBackApplication {
    public static void main(String[] args) {
        SpringApplication.run(VueBackApplication.class, args);
    }
}
