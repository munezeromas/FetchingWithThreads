package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class URLFetcherThread implements Runnable {

        String url;
       public URLFetcherThread(String url) {
            this.url=url;
        }
        @Override
                public void run(){
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request= HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String body = response.body();
                String[] lines=body.split("\n");
                for(String line:lines){}
                System.out.println("Thread [" + Thread.currentThread().getName() + "]" + "Url" + url);
                System.out.println("The status code is :" + response.statusCode());
            } catch(IOException|InterruptedException e) {
                System.err.println("[" + Thread.currentThread().getName() + "] Failed to fetch url" + url);
                e.printStackTrace();
            }
            catch (IllegalArgumentException e) {
                System.err.println("[" + Thread.currentThread().getName() +"Invalid URL" + url);
            }
        }
    }
