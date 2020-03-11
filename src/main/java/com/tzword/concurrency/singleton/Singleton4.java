package com.tzword.concurrency.singleton;

/**
 * 双重校验锁的单例模式(正确方式)
 */
public class Singleton4 {
    private static volatile Singleton4 singleton = null;

    //私有构造器使其他类无法直接通过new创建该类的实例
    public Singleton4() {
    }
    public static Singleton4 getInstance(){
        if (null == singleton){//操作①：第一次检查
            synchronized (Singleton4.class){
                if (null == singleton){//操作②：第二次检查
                    singleton = new Singleton4();//操作③
                }
            }
        }
        return singleton;
    }
    //添加了volatile,利用它的两个作用：
    //保障可见性：一个线程通过执行操作③修改了singleton变量的值，其他线程可以读取都相应的值（通过此操作①）
    //保障有序性：由于volatile能够禁止volatile变量写操作与该操作之前的任何读、写操作进行重排序，因此
    // 用volatile修饰instance相当于禁止JIT编译器以及处理器将子操作②重排序到子操作③。
}
