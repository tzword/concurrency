package com.tzword.concurrency.cyclicbarrier;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * juc中Cyclicbarrier线程工具类
 */
@Slf4j
public class CyclicbarrierExample2 {

    private  static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
    public static void main(String[] args) throws Exception{
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            es.execute(()->{
                try {
                    face(threadNum);
                } catch (Exception e) {
                    log.error("exception",e);
                }
            });
        }
        es.shutdown();
    }

    private static void face(int threadNum)throws Exception{
        Thread.sleep(1000);
        log.info("{} is ready",threadNum);
        try {
            //这里只等待2秒
            cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
        }catch (Exception e){
            log.error("exception",e);
        }
        log.info("{} is continue",threadNum);
    }
}
