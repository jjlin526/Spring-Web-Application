package com.oreilly.demo.json;

import org.springframework.stereotype.Component;

// wrapper for string
// class is to serialize into JSON form and deserialized on the response
@Component
public class Greeting {
    private String message;

    public Greeting() {

    }

    public Greeting(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Greeting{" +
                "message='" + message + '\'' + '}';
    }
}
