package com.oreilly.demo.json;

// wrapper for string
// class is to serialize into JSON form and deserialized on the response
public class Greeting {
    private final String message;

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
