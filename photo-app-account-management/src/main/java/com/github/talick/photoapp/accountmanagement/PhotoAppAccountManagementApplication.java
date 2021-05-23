package com.github.talick.photoapp.accountmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class PhotoAppAccountManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotoAppAccountManagementApplication.class, args);
    }

}
