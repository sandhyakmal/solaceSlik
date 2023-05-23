package org.example;



import org.example.services.RbsDoneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootRestApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestApplication.class,args);
    }

    private static final Logger logger = LoggerFactory.getLogger(RbsDoneService.class);



}