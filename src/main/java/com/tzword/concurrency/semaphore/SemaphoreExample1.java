package com.tzword.concurrency.semaphore;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量(一次拿到一个许可)
 */
@Slf4j
public class SemaphoreExample1 {
    public final static int threadcount = 10;
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < threadcount; i++) {
            final int threadNum = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();//拿到许可
                    test(threadNum);
                    semaphore.release(); //释放许可
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }
        log.info("finish");
        executorService.shutdown();
    }

    public static void test(int threadNum){
        try {
            log.info("{}",threadNum);
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
