package com.example.demomicrometer;

import io.micrometer.core.annotation.Timed;

import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SleepService {
    private final Random random = new Random(System.nanoTime());

    @Timed("sleep")
    @NewSpan
    public String sleep() {
        int i = random.nextInt(100);
        int sleep = (i >= 95) ? 400 : 50;
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Hello World!";
    }
}
