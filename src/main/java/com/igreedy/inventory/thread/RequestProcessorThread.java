package com.igreedy.inventory.thread;

import com.igreedy.inventory.request.Request;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

/**
 * 执行请求的工作线程
 * Created by igreedy on 2018/7/23
 */
public class RequestProcessorThread implements Callable<Boolean> {

    /**
     * 自己监控的内存队列，这个是线程的代码
     */
    private ArrayBlockingQueue<Request> queue;


    public RequestProcessorThread(ArrayBlockingQueue queue){
        this.queue = queue;
    }

    @Override
    public Boolean call() throws Exception {
        // 在当前的线程中不断去消费里面的请求
        try {
            // 不断的从queue中消费请求
            while (true){
                /**
                 * ArrayBlockingQueue
                 * Blocking就是说明，如果队列满了，或者是空的，
                 * 那么都会在执行操作的时候，阻塞住
                 */
                Request request = queue.take();
                // 执行这个request操作
                request.process();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return true;
    }
}
