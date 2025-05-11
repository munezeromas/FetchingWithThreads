package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class URLFetcherThread implements Runnable {

    private String url;

    public URLFetcherThread(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            String[] lines = body.split("\n");

            System.out.println("Thread [" + Thread.currentThread().getName() + "] fetching URL: " + url);
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("First 3 lines of the response:");

            int linesToPrint = Math.min(lines.length, 3);
            for (int i = 0; i < linesToPrint; i++) {
                System.out.println("Thread [" + Thread.currentThread().getName() + "]: " + lines[i]);
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("[" + Thread.currentThread().getName() + "] Failed to fetch URL: " + url);
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("[" + Thread.currentThread().getName() + "] Invalid URL: " + url);
        }
    }
}
