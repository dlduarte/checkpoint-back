package br.com.dld.checkpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CheckpointApplication {

    public static void main(String[] args) {
        SpringApplication.run(CheckpointApplication.class, args);
    }
}
