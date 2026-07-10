package com.homelander;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * ClassName:com.homelander.SkyApplication
 * Package:PACKAGE_NAME
 * Description:
 *
 * @Author Heisenberg
 * @Create 2026/7/7 23:46
 * @Version 1.0
 */
@SpringBootApplication
@EnableTransactionManagement
@Slf4j
public class SkyApplication{
    public static void main(String[] args) {
        // 启动Spring Boot 应用
        SpringApplication.run(SkyApplication.class, args);
        log.info("server started");
    }
}
