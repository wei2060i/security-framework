package com.security.securityframework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author sky
 */
@SpringBootApplication
@MapperScan("com.security.securityframework.dao")
public class SecurityFrameworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityFrameworkApplication.class, args);
    }

}
