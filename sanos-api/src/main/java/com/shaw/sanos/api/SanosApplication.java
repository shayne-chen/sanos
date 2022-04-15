package com.shaw.sanos.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shaw
 * @date 2022/3/22
 */
@SpringBootApplication(scanBasePackages = "com.shaw.sanos")
public class SanosApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(SanosApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("----------Sanos Register Center Started----------");
    }

}
