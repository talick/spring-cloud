package com.github.talick.photoapp.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class PhotoAppApiConfigServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotoAppApiConfigServiceApplication.class, args);
    }

}
