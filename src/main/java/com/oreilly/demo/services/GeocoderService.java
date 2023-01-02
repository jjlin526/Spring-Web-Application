package com.oreilly.demo.services;

import com.oreilly.demo.json.Response;
import com.oreilly.demo.json.Site;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// class that provides a business service for geocoding addresses using the Google Maps Geocoding API
@Service
public class GeocoderService {
    private static final String KEY = "AIzaSyDw_d6dfxDEI7MAvqfGXEIsEMwjC1PWRno";

    private final WebClient client;

    public GeocoderService(WebClient.Builder builder) {
        client = builder.baseUrl("https://maps.googleapis.com").build();
    }

    // formulated the complete URL with an encoded address and convert it to a Response object, using Java 11
    public Site getLatLng(String... address) { // take variable number of string arguments
        String encoded = Stream.of(address)
        .map(component -> URLEncoder.encode(component, StandardCharsets.UTF_8)) // encode the address components
                .collect(Collectors.joining(","));
        String path = "/maps/api/geocode/json";
        Response response = client.get()
                // append encoded address and an API key to the base URL of the WebClient
                .uri(uriBuilder -> uriBuilder.path(path)
                        .queryParam("address", encoded)
                        .queryParam("key", KEY)
                        .build())
                // make HTTP GET request to the constructed URL
                .retrieve()
                // convert response to a Response object
                .bodyToMono(Response.class)
                // wait for the response to be returned for a specified duration
                .block(Duration.ofSeconds(2));
        // return a Site object using the formatted address and lat/long
        return new Site(response.getFormattedAddress(),
                response.getLocation().getLat(),
                response.getLocation().getLng());
    }
}
