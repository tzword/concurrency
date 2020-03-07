package com.tzword.concurrency.cyclicbarrier;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * juc中Cyclicbarrier线程工具类
 */
@Slf4j
public class CyclicbarrierExample1 {

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
        //到这里就进入等待其他线程状态
        cyclicBarrier.await();
        log.info("{} is continue",threadNum);
    }
}
