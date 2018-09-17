package me.renkai.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstRest {

    @GetMapping("/hello")
    public String first() {
        return "hello world!";
    }
}
