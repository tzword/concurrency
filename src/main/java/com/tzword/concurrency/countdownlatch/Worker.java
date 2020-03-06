package com.tzword.concurrency.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by tzwor on 2020/3/6.
 */
public class Worker implements Runnable{
    private String name;
    private CountDownLatch latch;

    public Worker(String name, CountDownLatch latch) {
        this.name = name;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            System.out.println( this.name + "事情干完了" );
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
