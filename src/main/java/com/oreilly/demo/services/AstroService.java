package com.oreilly.demo.services;

import com.oreilly.demo.json.AstroResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// service is a component
// plays role of a service in a layered architecture
// layered architecture: presentation (controllers/views), service layer (transactions/business logic), persistence layer (database)
// spring has to manage this class for it to be autowired
@Service
public class AstroService {

    private final RestTemplate template;

    // if there is only a single constructor, spring will automatically autowire the arguments
    @Autowired
    public AstroService(RestTemplateBuilder rtBuilder) {
        template = rtBuilder.build();
    }

    public AstroResult getAstronauts() {
        String url = "http://api.open-notify.org/astros.json";
        return template.getForObject(url, AstroResult.class);
    }
}
