package com.edu.nchu.distributed.thread;

import java.util.concurrent.*;

/**
 * BlockQueue 阻塞队列小样
 * Created by fujianjian on 2017/2/16.
 */
public class BlockQueueDemo {

    private BlockingQueue<String> queue = new LinkedBlockingDeque<String>();

    public static void main(String[] args) throws Exception {
        BlockQueueDemo demo = new BlockQueueDemo();

        Thread tt = new Thread(){
            /**
             * If this thread was constructed using a separate
             * <code>Runnable</code> run object, then that
             * <code>Runnable</code> object's <code>run</code> method is called;
             * otherwise, this method does nothing and returns.
             * <p>
             * Subclasses of <code>Thread</code> should override this method.
             *
             * @see #start()
             * @see #stop()
             * @see #Thread(ThreadGroup, Runnable, String)
             */
            @Override
            public void run() {
                while (true){
                    demo.takeQueue();
                    try {
                        TimeUnit.MILLISECONDS.sleep(3000);
                    } catch (InterruptedException e){}
                }
            }
        };
        tt.start();

        Thread t = new Thread() {
            /**
             * If this thread was constructed using a separate
             * <code>Runnable</code> run object, then that
             * <code>Runnable</code> object's <code>run</code> method is called;
             * otherwise, this method does nothing and returns.
             * <p>
             * Subclasses of <code>Thread</code> should override this method.
             *
             * @see #start()
             * @see #stop()
             * @see #Thread(ThreadGroup, Runnable, String)
             */
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    demo.submitQueue();
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                    } catch (InterruptedException e) {}
                }

            }
        };
        t.start();

    }

    public void submitQueue(){
        String obj = "testQueue";
        System.out.println("当前队列长度：" + queue.size());
        System.out.println(String.format("线程名称[%s] 现在添加一个Queue", Thread.currentThread().getName()));
        queue.add(obj);
    }
    public void takeQueue(){
        String ret = null;
        try {
            ret = queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("线程名称[%s], 当前获取的队列消息：%s", Thread.currentThread().getName(), ret));
    }
}
