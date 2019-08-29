package com.vapasians.shopoholics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ShopoholicsApplication {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(ShopoholicsApplication.class, args);
        System.out.println(">>> Datasource instance: " + ctx.getBean("dataSource"));
    }

}
