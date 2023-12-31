package br.nikolastrapp.receba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RecebaApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecebaApplication.class, args);
    }

}
