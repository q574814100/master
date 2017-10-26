package com.kee.getway.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy //zuul 开启
public class GetWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GetWayApplication.class,args);
    }
}
