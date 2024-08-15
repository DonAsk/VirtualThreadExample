package com.digital.ace.java.virtualThreadExample.myTask;

import static java.lang.StrictMath.atan;
import static java.lang.StrictMath.cbrt;
import static java.lang.StrictMath.tan;
import java.util.concurrent.Callable;
import java.util.stream.IntStream;

public class HighComputationTask implements Callable<String> {

    private static final int LOOP_COUNT = 1_000_000;

    @Override
    public String call() throws Exception {
        long start = System.currentTimeMillis();
        IntStream.range(0, LOOP_COUNT).forEach(i -> {
            double d = tan(atan(tan(atan(tan(atan(tan(atan(tan(atan(123456789.123456789))))))))));
            cbrt(d);
        });
        long timeTakenMs = System.currentTimeMillis() - start;
        String retMsg = timeTakenMs + " >> " + Thread.currentThread().getName() + " done.";
        return retMsg;
    }

}