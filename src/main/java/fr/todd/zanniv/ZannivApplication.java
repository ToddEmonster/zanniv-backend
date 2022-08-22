package fr.todd.zanniv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan("fr.*")
public class ZannivApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZannivApplication.class, args);
    }
}