package com.mjc.stage2;

public class ThreadSafeSingleton {

    private static volatile ThreadSafeSingleton instance;
    private static final Object MONITOR = new Object();


    private ThreadSafeSingleton() {
    }

    public static ThreadSafeSingleton getInstance(){
        if(instance == null){
            synchronized (MONITOR) {
                instance = new ThreadSafeSingleton();
            }
        }
        return instance;
    }
}
