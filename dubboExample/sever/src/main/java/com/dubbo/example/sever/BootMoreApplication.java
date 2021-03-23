package com.dubbo.example.sever;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * @description:
 * @author: Qr
 * @create: 2021-02-16 16:13
 **/
@SpringBootApplication
@PropertySource(value = "classpath:application.properties")
@ImportResource(value = {"classpath:spring/spring-jdbc.xml","classpath:spring/spring-dubbo.xml"})
@MapperScan(basePackages = "com.dubbo.example.model.mapper")
public class BootMoreApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BootMoreApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BootMoreApplication.class, args);
        System.out.println(System.getProperty("classpath"));
        System.out.println(System.getProperty("java.class.path"));
    }
}
