package com.example.ridespy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RideSpyApplication {

    public static void main(String[] args) {
        SpringApplication.run(RideSpyApplication.class, args);
    }

}
