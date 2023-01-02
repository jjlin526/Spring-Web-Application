package com.oreilly.demo.services;

import com.oreilly.demo.json.AstroResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

// service is a component
// plays role of a service in a layered architecture
// layered architecture: presentation (controllers/views), service layer (transactions/business logic), persistence layer (database)
// spring has to manage this class for it to be autowired
@Service
public class AstroService {

    private final RestTemplate template;
    private final WebClient client;

    // if there is only a single constructor, spring will automatically autowire the arguments
    @Autowired
    public AstroService(RestTemplateBuilder rtBuilder, WebClient.Builder wcBuilder) {
        template = rtBuilder.build();
        client = wcBuilder.baseUrl("http://api.open-notify.org").build();
    }

    // synchronous
    public AstroResult getAstronauts() {
        String url = "http://api.open-notify.org/astros.json";
        return template.getForObject(url, AstroResult.class);
    }

    // asynchronous
    public AstroResult getAstronautsWC() {
        return client.get() // GET request
                .uri("/astros.json") // appended to base URL
                .accept(MediaType.APPLICATION_JSON) // set request header
                .retrieve() // schedule retrieve
                .bodyToMono(AstroResult.class) // convert body to mono of AstroResult
                // wait max of 2 seconds to return AstroResult, or else return null
                .block(Duration.ofSeconds(2));
    }
}
