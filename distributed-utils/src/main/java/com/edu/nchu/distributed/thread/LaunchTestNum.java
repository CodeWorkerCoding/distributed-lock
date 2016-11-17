package com.edu.nchu.distributed.thread;

/**
 * Created by Alen on 2016/11/15.
 */
public class LaunchTestNum {

    public static void main_bak(String[] args) {
        ThreadGroup group = new ThreadGroup("test num");
        CustomThread customThread = new CustomThread();
        Thread thread1 = new Thread(group, customThread);
        thread1.start();
        Thread thread2 = new Thread(group, customThread);
        thread2.start();
        Thread thread3 = new Thread(group, customThread);
        thread3.start();
        Thread thread4 = new Thread(group, customThread);
        thread4.start();

    }
}
