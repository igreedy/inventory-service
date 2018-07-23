package com.igreedy.inventory.thread;

import com.igreedy.inventory.request.Request;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

/**
 * 执行请求的工作线程
 * Created by igreedy on 2018/7/23
 */
public class WorkerThread implements Callable<Boolean> {

    /**
     * 自己监控的内存队列，这个是线程的代码
     */
    private ArrayBlockingQueue<Request> queue;


    public WorkerThread(ArrayBlockingQueue queue){
        this.queue = queue;
    }

    @Override
    public Boolean call() throws Exception {
        // 在当前的线程中不断去消费里面的请求
        while (true){
            // 不断的从queue中消费请求
            break;
        }
        return true;
    }
}
