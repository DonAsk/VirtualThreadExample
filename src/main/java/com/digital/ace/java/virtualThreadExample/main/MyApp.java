package com.digital.ace.java.virtualThreadExample.main;

import com.digital.ace.java.virtualThreadExample.myTask.HighComputationTask;
import com.digital.ace.java.virtualThreadExample.myTask.ReadLocalFileTask;
import com.digital.ace.java.virtualThreadExample.myTask.ReadURLTask;
import com.digital.ace.java.virtualThreadExample.myTask.SleepThreadTask;
import com.digital.ace.java.virtualThreadExample.testcase.MyTestCase;

public class MyApp {

    public static void main(String[] args) {

        System.out.printf("***********START case SleepThreadTask **************\n");
        var test1 = new MyTestCase(new SleepThreadTask());
        test1.runTests();
        System.out.printf("***********END case SleepThreadTask **************\n");

        System.out.printf("***********START case ReadURLTask **************\n");
        var test2 = new MyTestCase(new ReadURLTask());
        test2.runTests(); 
        System.out.printf("***********END case ReadURLTask **************\n");
        
        System.out.printf("***********START case ReadLocalFileTask **************\n");
        var test3 = new MyTestCase(new ReadLocalFileTask());
        test3.runTests();
        System.out.printf("***********END case ReadLocalFileTask **************\n");
        
        System.out.printf("***********START case HighComputationTask **************\n");
        var test4 = new MyTestCase(new HighComputationTask());
        test4.runTests();
        System.out.printf("***********END case HighComputationTask **************\n");        

    }
}
