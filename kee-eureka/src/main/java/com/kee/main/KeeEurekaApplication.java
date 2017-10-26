package com.kee.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer// eureka 服务开启
public class KeeEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeeEurekaApplication.class,args);
    }
}
