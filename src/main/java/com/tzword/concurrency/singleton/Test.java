package com.tzword.concurrency.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tzwor on 2020/3/11.
 */
public class Test {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            final int threadNum = i;
            executorService.execute(()->
                System.out.println(threadNum + "--" + Singleton3.getInstance())
            );
        }
        executorService.shutdown();
    }
}
