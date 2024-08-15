package com.digital.ace.java.virtualThreadExample.myTask;

import java.util.concurrent.Callable;

public class SleepThreadTask implements Callable<String> {

    @Override
    public String call() throws Exception {
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long timeTakenMs = System.currentTimeMillis() - start;
        String retMsg = timeTakenMs + " >> " + Thread.currentThread().getName() + " done.";
        return retMsg;
    }

}
