package com.tzword.concurrency.thread;

/**
 * Created by tzwor on 2020/3/10.
 */
public class ThreadStartVisibility {
    static int data = 0;

    public static void main(String[] args) throws Exception{
        Thread thread = new Thread(()->{
            try {
                Thread.sleep(50);
                System.out.println(data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        data = 1;
        thread.start();
        Thread.sleep(50);
//        data = 2;
        thread.join();
    }

}
