package com.gaogao.cucaishop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gaogao.cucaishop.*.mappers")
public class CuCaiShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(CuCaiShopApplication.class, args);
    }

}
