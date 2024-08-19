package com.digital.ace.java.virtualThreadExample.testcase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class MyTestCase {

    private static final int NUM_OF_THREADS = 100;
    // private static final int NUM_OF_THREADS = 100_000;    

    private static final int CHAR_TO_SHOW = 70;

    private final Callable<String> task;

    private long newCachedThreadPoolTime = 0;
    private long newVirtualThreadPerTaskTime = 0;

    public MyTestCase(Callable<String> taskIn) {
        task = taskIn;
    }

    public void runTests() {
        testNewCachedThreadPool();
        testNewVirtualThreadPerTask();

        System.out.println("NewCachedThreadPool Time taken: " + newCachedThreadPoolTime + "ms");
        System.out.println("NewVirtualThreadPerTask Time taken: " + newVirtualThreadPerTaskTime + "ms");
    }

    private void testNewCachedThreadPool() {
        long start = System.currentTimeMillis();
        ExecutorService es = Executors.newCachedThreadPool();
        List<Callable<String>> tasks = new ArrayList<>();

        IntStream.range(0, NUM_OF_THREADS).forEach(i -> tasks.add(task));

        try {
            List<Future<String>> futures = es.invokeAll(tasks);
            futures.stream().forEach(s -> {
                try {
                    String printMsg = "newCachedThreadPool >> " + s.get().substring(0, Math.min(s.get().length(), CHAR_TO_SHOW));
                    System.out.println(printMsg);
                } catch (InterruptedException | ExecutionException e) {
                    System.err.println(e.getMessage());
                }
            });
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
        es.shutdown();
        
        newCachedThreadPoolTime = System.currentTimeMillis() - start;
    };

    private void testNewVirtualThreadPerTask() {
        long start = System.currentTimeMillis();
        ExecutorService es = Executors.newVirtualThreadPerTaskExecutor();

        List<Callable<String>> tasks = new ArrayList<>();

        IntStream.range(0, NUM_OF_THREADS).forEach(i -> tasks.add(task));

        try {
            List<Future<String>> futures = es.invokeAll(tasks);
            futures.stream().forEach(s -> {
                try {
                    // only print up to CHAR_TO_SHOW of the string
                    String printMsg = "newVirtualThreadPerTaskExecutor >> " + s.get().substring(0, Math.min(s.get().length(), CHAR_TO_SHOW));
                    System.out.println(printMsg);
                } catch (InterruptedException | ExecutionException e) {
                    System.err.println(e.getMessage());
                }
            });
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
        es.shutdown();
        
        newVirtualThreadPerTaskTime = System.currentTimeMillis() - start;
    };

}
