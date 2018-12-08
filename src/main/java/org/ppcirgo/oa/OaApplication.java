package org.ppcirgo.oa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = {"org.ppcirgo.oa.mapper"})
public class OaApplication {
    public static void main(String[] args) {
        SpringApplication.run(OaApplication.class, args);
    }
}
