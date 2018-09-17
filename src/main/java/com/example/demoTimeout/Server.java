package com.example.demoTimeout;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.Thread.*;

@RestController
public class Server {
    @RequestMapping("/echo")
    public String home() {
        try {
            //sleep on purpose, to check rest template timeout or not.
            sleep(200* 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello World!";
    }
}
