package com.tzword.concurrency.semaphore;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量(尝试获取许可，等待的超时时间，超时就不获取了)
 */
@Slf4j
public class SemaphoreExample4 {
    public final static int threadcount = 10;
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < threadcount; i++) {
            final int threadNum = i;
            executorService.execute(()->{
                try {
                    //尝试获取许可，等待的超时时间，这里等待200毫秒，超时就不获取了
                    if (semaphore.tryAcquire(200, TimeUnit.MILLISECONDS)){
                        test(threadNum);
                        semaphore.release(); //释放许可
                    }
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
