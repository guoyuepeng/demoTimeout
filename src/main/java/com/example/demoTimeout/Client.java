package com.example.demoTimeout;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Client {

    private RestTemplate restTemplate = new RestTemplate();

    @RequestMapping("/call")
    public String callServer() {
        try {
            System.out.println("...handling start...");
            ResponseEntity<String> response
                    = restTemplate.getForEntity("http://localhost:8080/echo", String.class);
            String body = response.getBody();
            System.out.println("...handling end...");
            return body;
        } catch (Throwable t) {
            return t.getMessage();
        }
    }
}
