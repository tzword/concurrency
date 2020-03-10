package com.tzword.concurrency.thread;

/**
 * Created by tzwor on 2020/3/10.
 */
public class ThreadExample1 {
    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        thread1.start();
        System.out.println(Thread.currentThread().getName());
        Thread thread2 = new Thread(new Runnable1());
        thread2.start();
        System.out.println(Thread.currentThread().getName());
    }
}
