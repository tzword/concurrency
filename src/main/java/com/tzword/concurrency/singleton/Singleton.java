package com.tzword.concurrency.singleton;

/**
 * 单线程的单例模式
 */
public class Singleton{
    private static Singleton singleton = null;

    //私有构造器使其他类无法直接通过new创建该类的实例
    public Singleton() {
    }
    public static Singleton getInstance(){
        if (null == singleton){//操作①
            singleton = new Singleton();//操作②
        }
        return singleton;
    }

    // 这样在多线程中会出现问题，加入两个线程T1,T2同时执行到操作①，
    // 但是T2执行的快，先去new了一个对象，但是T1不知到，它还会再次new，
    // 这样就会出现多个实例，所以是线程不安全的。

}
