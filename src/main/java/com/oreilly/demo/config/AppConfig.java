package com.oreilly.demo.config;

import com.oreilly.demo.json.Greeting;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    // @Bean annotation is used to populate the application context
    @Bean
    public Greeting defaultGreeting() {
        return new Greeting("Hello, World!");
    }

    @Bean
    public Greeting whatUpGreeting() {
        return new Greeting("What up?");
    }
}
