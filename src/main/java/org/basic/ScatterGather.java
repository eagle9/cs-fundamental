package org.basic;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ScatterGather {
    ExecutorService threadPool;
    private static Logger logger = LoggerFactory.getLogger( ScatterGather.class.getName() );
    public void run() {
        threadPool = Executors.newFixedThreadPool(4);
        try {
            Set<Integer> prices = getPrices( 1 );
            for (Integer price : prices) {
                System.out.println(price);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.shutdown();
    }

    public static void main(String[] args) {

        new ScatterGather().run();

    }
    private Set<Integer>  getPrices(int productId) throws InterruptedException {
        Set<Integer> prices = Collections.synchronizedSet( new HashSet<>(  ) );

        CountDownLatch latch = new CountDownLatch( 3 );
        List<String> urlList = Arrays.asList("url1", "url2", "url3");
        for (String url: urlList) {
            threadPool.submit( new Task( url, productId, prices, latch ) );
        }
        latch.await( 3, TimeUnit.SECONDS );

        return prices;
    }

    class Task  implements Runnable {
        private String url;
        private int productId;
        private Set<Integer> prices;
        private CountDownLatch latch;


        public Task(String url, int productId, Set<Integer> prices, CountDownLatch latch) {
            this.url = url;
            this.prices = prices;
            this.productId = productId;
            this.latch = latch;
        }

        @Override
        public void run() {
            int price = new Random().nextInt(10);
            prices.add(price);
            logger.info("from url=" + url + ", price=" + price);
            latch.countDown();
        }

    }
}
