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
    }
}
