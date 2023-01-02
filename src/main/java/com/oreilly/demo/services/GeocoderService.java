package com.oreilly.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GeocoderService {
    private static final String KEY = "AIzaSyDw_d6dfxDEI7MAvqfGXEIsEMwjC1PWRno";

    private final WebClient client;

    public GeocoderService(WebClient.Builder builder) {
        client = builder.baseUrl("https://maps.googleapis.com").build();
    }


}
