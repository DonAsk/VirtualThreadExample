package com.digital.ace.java.virtualThreadExample.myTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.Callable;

public class ReadURLTask implements Callable<String> {

    static final private String URI_STRING = "https://www.channelnewsasia.com/";

    @Override
    public String call() throws Exception {
        long start = System.currentTimeMillis();
        StringBuilder response = new StringBuilder();
        try {
            URI uri = URI.create(URI_STRING);
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Read the response body
            try (
                InputStream in = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            ) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }                
            }
            connection.disconnect();
        } catch (IOException e) {
            return "error can not get URL " + e.getMessage();
        }

        long timeTakenMs = System.currentTimeMillis() - start;
        String retMsg = "time >> " + timeTakenMs + "ms >> " + Thread.currentThread().getName() + " id : " + Thread.currentThread().threadId() + " done.";
        return retMsg;
    }
}
