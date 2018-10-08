package me.renkai.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstRest {

    private class Body {
        private String message;

        Body(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
    @GetMapping("/hello")
    public Body first() {
        return new Body("hello world!");
    }
}
