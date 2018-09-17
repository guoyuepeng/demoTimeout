package com.example.demoTimeout;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class ClientWithTimeout {

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        //3 seconds
        int timeout = 3000;
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
                = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeout);
        clientHttpRequestFactory.setReadTimeout(timeout);
        clientHttpRequestFactory.setConnectionRequestTimeout(timeout);
        return clientHttpRequestFactory;
    }

    private RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());

    @RequestMapping("/callIn3Seconds")
    public String callServer() {
        try {
            System.out.println("...callWithTimeOut handling start...");
            ResponseEntity<String> response
                    = restTemplate.getForEntity("http://localhost:8080/echo", String.class);
            String body = response.getBody();
            return body;
        } catch (Throwable t) {
            System.out.println("...callWithTimeOut handling end...");
            return t.getMessage();
        }
    }
}
