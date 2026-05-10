package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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
                    doWork();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            Future<String> future = executor.submit(() -> {
                Thread.sleep(1500);
                return "Resultado da tarefa Callable " + taskId;
            });
            futures.add(future);
        }

        for (Future<String> future : futures) {
            try {
                String resultado = future.get();
                System.out.println(resultado);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        shutdownAndAwaitTermination(executor);
    }

    private static void doWork() throws InterruptedException {
        Thread.sleep(1000);
    }

    private static void shutdownAndAwaitTermination(ExecutorService executor) {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
                if (!executor.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Pool não terminou corretamente");
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
        System.out.println("ExecutorService encerrado.");
    }
}
