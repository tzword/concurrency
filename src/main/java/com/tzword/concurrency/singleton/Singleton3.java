package com.tzword.concurrency.singleton;

/**
 * 双重校验锁的单例模式(错误方式)
 */
public class Singleton3 {
    private static Singleton3 singleton = null;

    //私有构造器使其他类无法直接通过new创建该类的实例
    public Singleton3() {
    }
    public static Singleton3 getInstance(){
        if (null == singleton){//操作①：第一次检查
            synchronized (Singleton3.class){
                if (null == singleton){//操作②：第二次检查
                    singleton = new Singleton3();//操作③
                }
            }
        }
        return singleton;
    }
    // 这种有可能出现，一个线程执行操作①的时候发现singleton不是null,
    // 于是直接返回这个singleton的实例，而这个实例有可能是未初始化的，
    // 这样有可能会导致程序出错
}
