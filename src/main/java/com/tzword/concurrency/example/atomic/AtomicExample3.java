package com.tzword.concurrency.example.atomic;

import com.tzword.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;

@Slf4j
@ThreadSafe
public class AtomicExample3 {
   private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        //expect update  两个参数表示，当是expect的值修改成update的值
        count.compareAndSet(0,2);///2
        count.compareAndSet(0,1);//no
        count.compareAndSet(1,3);//no
        count.compareAndSet(2,4);//4
        count.compareAndSet(3,5);//no
        log.info("count:{}",count.get());
    }
}
