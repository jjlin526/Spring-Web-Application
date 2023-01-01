package com.oreilly.demo;

import com.oreilly.demo.json.Greeting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads() {
        assertNotNull(context); // show that it got autowired in
        int count = context.getBeanDefinitionCount();
        System.out.println("There are " + count + " beans in the application context");
        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
    }

    @Test
    void noGreetingInAppCtx() {
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> context.getBean(Greeting.class));
    }
}
