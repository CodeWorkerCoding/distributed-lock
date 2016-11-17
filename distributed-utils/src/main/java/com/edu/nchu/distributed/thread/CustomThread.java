package com.edu.nchu.distributed.thread;

/**
 * Created by Alen on 2016/11/15.
 */
public class CustomThread implements Runnable {

    private  TestNum num  = new TestNum();


    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
//            num.addNum();
//            System.out.println("thread name:【"+Thread.currentThread().getName()+"】, the number num["+num.getNum()+"]");
            num.addUnsafeNum();
            System.out.println("thread name:【"+Thread.currentThread().getName()+"】, the number num["+num.getUnSafeNum()+"]");
        }

    }


}
