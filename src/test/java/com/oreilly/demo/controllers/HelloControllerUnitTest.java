package com.oreilly.demo.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import static org.junit.jupiter.api.Assertions.*;

class HelloControllerUnitTest {
    @Test
    public void sayHello() {
        // instantiate class
        HelloController controller = new HelloController();
        // assign class to reference of type model (an interface)
        Model model = new BindingAwareModelMap();
        // executables: lambda expressions
        String result = controller.sayHello("World", model);
        assertAll(
                () -> assertEquals("World", model.getAttribute("user")),
                () -> assertEquals("hello", result)
        );
    }
}

