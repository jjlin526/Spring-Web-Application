package com.oreilly.demo.services;

import com.oreilly.demo.json.Response;
import com.oreilly.demo.json.Site;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public GeocoderService(WebClient.Builder builder) {
        client = builder.baseUrl("https://maps.googleapis.com").build();
    }

    // formulated the complete URL with an encoded address and convert it to a Response object, using Java 11
    public Site getLatLng(String... address) { // take variable number of string arguments
        // use factory method to create a stream from a var ags parameter (address array)
        // a stream is a sequence of elements that can be processed sequentially or in parallel
        // this allows filtering, mapping, reducing, or collecting
        String encoded = Stream.of(address)
        .map(component -> URLEncoder.encode(component, StandardCharsets.UTF_8)) // encode the address components using lambda
                // perform terminal operation - concatenate the stream of strings into a single string
                .collect(Collectors.joining(","));
        String path = "/maps/api/geocode/json";
        // make GET request using WebClient
        Response response = client.get()
                // append encoded address and an API key to the base URL of the WebClient
                .uri(uriBuilder -> uriBuilder.path(path) // set path of URL
                        // add query parameters to the URI and return UriBuilder object for method chaining
                        .queryParam("address", encoded)
                        .queryParam("key", KEY)
                        // build the URI using the accumulated path and query parameters, return as URI object
                        .build())
                // initiate HTTP GET request using the constructed URL to the server - returns a ResponseSpec object
                .retrieve()
                // convert ResponseSpec object to a Mono object (reactive type that represents a promise to a Response object)
                // this uses the reactive extension 'bodyToMono' method of the ResponseSpec object
                .bodyToMono(Response.class)
                // wait for the response to be returned for a specified duration using utility method
                .block(Duration.ofSeconds(2));
        // return a Site object using the formatted address and lat/long
        return new Site(response.getFormattedAddress(),
                response.getLocation().getLat(),
                response.getLocation().getLng());
    }
}
