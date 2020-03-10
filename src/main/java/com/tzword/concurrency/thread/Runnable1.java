package com.tzword.concurrency.thread;

/**
 * Created by tzwor on 2020/3/10.
 */
public class Runnable1 implements Runnable{
    @Override
    public void run() {
        System.out.println("nihao");
        System.out.println("当前线程："+Thread.currentThread().getName());
    }
}
