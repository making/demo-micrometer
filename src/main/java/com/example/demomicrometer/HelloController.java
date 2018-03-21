package com.example.demomicrometer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final SleepService sleepService;

    public HelloController(SleepService sleepService) {
        this.sleepService = sleepService;
    }

    @GetMapping
    public String hello() {
        return this.sleepService.sleep();
    }
}
