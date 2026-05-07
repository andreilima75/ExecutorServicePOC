package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServicePOC {

    static void main() {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        System.out.println("Starting...");

        List<Future<String>> futures = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Tarefa " + taskId + " executada por: " +
                        Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
    }
}
