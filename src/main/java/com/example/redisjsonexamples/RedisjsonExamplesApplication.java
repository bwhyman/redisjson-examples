package com.example.redisjsonexamples;

import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRedisDocumentRepositories
public class RedisjsonExamplesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisjsonExamplesApplication.class, args);
    }

}
