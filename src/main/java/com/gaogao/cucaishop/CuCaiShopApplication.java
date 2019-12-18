package com.gaogao.cucaishop;

import com.gaogao.cucaishop.adm.config.AppProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@MapperScan("com.gaogao.cucaishop.*.mappers")
@EnableConfigurationProperties(AppProperties.class)
//@EnableResourceServer
public class CuCaiShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(CuCaiShopApplication.class, args);
    }

}
