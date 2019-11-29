package com.tzword.concurrency.example.count;

import com.tzword.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@NotThreadSafe
public class CountExample2 {
    //请求总数
    public static int CLIENT_TOTAL = 5000;
    //同时并发执行的线程数
    public static int THREAD_TOTAL = 50;
    public static AtomicInteger COUNT = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(THREAD_TOTAL);
        final CountDownLatch countDownLatch = new CountDownLatch(CLIENT_TOTAL);
        for (int i = 0; i < CLIENT_TOTAL; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}", COUNT.get());
    }

    public static void add(){
        //先做增加操作，再获取当前的值
        COUNT.incrementAndGet();
        //先获取当前的值，再做增加操作
        //COUNT.getAndAccumulate();
    }
}
