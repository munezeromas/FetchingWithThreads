package org.example;

public class Main {
    public static void main(String[] args) {
        String[] urls = {
                "https://www.json.org/json-en.html",
                "https://www.vogue.com/",
                "https://igihe.com/index.php"
        };

        System.out.println("Main thread continues processing...");

        int i = 1;
        for (String url : urls) {
            Thread t = new Thread(new URLFetcherThread(url));
            t.setName("FetcherThread-" + i++);
            t.start();
        }
    }
}
