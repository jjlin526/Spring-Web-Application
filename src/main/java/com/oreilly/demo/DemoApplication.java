package com.oreilly.demo;

import com.oreilly.demo.json.Greeting;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

// class is config file and contains main method
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // @Bean annotation is used to populate the application context
    @Bean
    public Greeting defaultGreeting() {
        return new Greeting("Hello, World!");
    }
}
