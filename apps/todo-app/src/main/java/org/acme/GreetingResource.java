package org.acme;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingResource {

    @Value("greeting.message")
    String msg;

    @GetMapping(path = "/hello")
    public String hello() {
        return msg;
    }
}