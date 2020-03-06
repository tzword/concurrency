package com.tzword.concurrency.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by tzwor on 2020/3/6.
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        // 两个线程，两个线程工作完毕之后，才唤醒主线程
        CountDownLatch countDownLatch = new CountDownLatch(5);
        Worker w1 = new Worker("zhangsan",countDownLatch);
        Worker w2 = new Worker("lisi",countDownLatch);
        Worker w3 = new Worker("wangwu",countDownLatch);
        Worker w4 = new Worker("zhaoliu",countDownLatch);
        Worker w5 = new Worker("liqi",countDownLatch);
        new Thread(w1).start();
        new Thread(w2).start();
        new Thread(w3).start();
        new Thread(w4).start();
        new Thread(w5).start();
        try {
            countDownLatch.await();
            System.out.println("工人工作完成了...结算工钱...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
