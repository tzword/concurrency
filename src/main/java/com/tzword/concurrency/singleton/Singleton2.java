package com.tzword.concurrency.singleton;

/**
 * 多线程线程安全的单例模式
 */
public class Singleton2 {
    private static Singleton2 singleton = null;

    //私有构造器使其他类无法直接通过new创建该类的实例
    public Singleton2() {
    }
    public static Singleton2 getInstance(){
        synchronized (Singleton2.class){
            if (null == singleton){//操作①
                singleton = new Singleton2();//操作②
            }
        }
        return singleton;
    }
}
