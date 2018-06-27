package com.qicode.kakaxicm.networkframework.network;

import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenming on 2018/6/27
 */
public class ThreadPoolManager {
    private static ThreadPoolManager sInstance = new ThreadPoolManager();

    private LinkedBlockingDeque<Future<?>> taskQueue = new LinkedBlockingDeque<>();
    private ThreadPoolExecutor threadPoolExecutor;

    public static ThreadPoolManager getsInstance() {
        return sInstance;
    }


    private final RejectedExecutionHandler rejectedHandler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                taskQueue.put(new FutureTask<>(r, null));//线程池拒绝，则放到阻塞队列
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private ThreadPoolManager() {
        threadPoolExecutor = new ThreadPoolExecutor(4, 10, 20, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(6), rejectedHandler);
        //开启消费者轮询模式
        threadPoolExecutor.execute(enrollRunnable);
    }

    private Runnable enrollRunnable = new Runnable() {
        @Override
        public void run() {
            while (true) {//轮询
                FutureTask futureTask = null;
                try {
                    //此处阻塞
                    futureTask = (FutureTask) taskQueue.take();
                    Log.e("NetWork", "等待队列大小: " + taskQueue.size());
                } catch (InterruptedException e) {

                }
                if (futureTask != null) {
                    threadPoolExecutor.execute(futureTask);
                }
                Log.e("NetWork", "线程池大小: " + threadPoolExecutor.getPoolSize());
            }
        }
    };

    /**
     * 生产者
     *
     * @param futureTask
     * @param <T>
     * @throws InterruptedException
     */
    public <T> void execute(FutureTask<T> futureTask) {
        try {
            taskQueue.put(futureTask);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
