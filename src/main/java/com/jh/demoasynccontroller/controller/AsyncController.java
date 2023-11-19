package com.jh.demoasynccontroller.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class AsyncController {

    private final ExecutorService executorService = Executors.newCachedThreadPool();


    @GetMapping(path = "/test-async")
    public CompletableFuture<String> asyncEndpoint() {
        return CompletableFuture.supplyAsync(() -> {
            // Simula una operación larga
            try {
                int count = 0;
                Thread.sleep(5000);
                for (int i = 0;i<1000000;i++){
                    count++;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Resultado de la operación asincrónica";
        }, executorService);
    }

}
