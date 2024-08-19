package com.digital.ace.java.virtualThreadExample.myTask;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class ReadLocalFileTask implements Callable<String> {

    private static final String FILE_NAME = "randomReadme.txt";

    @Override
    public String call() throws Exception {
        long start = System.currentTimeMillis();
        StringBuilder response = new StringBuilder();
        URL resource = getClass().getClassLoader().getResource(FILE_NAME);
        if (resource == null) {
            throw new IllegalArgumentException("File not found!");
        } else {

            try (
                FileInputStream fileInputStream = new FileInputStream(resource.getFile());
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
            ) {
                List<String> doc = bufferedReader.lines().collect(Collectors.toList());
                doc.forEach(s -> response.append(s));

                bufferedReader.close();
                inputStreamReader.close();
                fileInputStream.close();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }

        }

        long timeTakenMs = System.currentTimeMillis() - start;
        String retMsg = "time >> " + timeTakenMs + "ms >> " + Thread.currentThread().getName() + " id : " + Thread.currentThread().threadId() + " done.";
        return retMsg;
    }

}