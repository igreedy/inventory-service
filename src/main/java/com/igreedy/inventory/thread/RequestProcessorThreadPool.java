package com.igreedy.inventory.thread;

import com.igreedy.inventory.request.Request;
import com.igreedy.inventory.request.RequestQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 请求处理线程池：单例
 * Created by igreedy on 2018/7/23
 */
public class RequestProcessorThreadPool {


    /**
     * 在实际项目中，你设置线程池大小是多少，每个线程监控的那个内存队列的大小是多少
     * 都可以做到一个外部的配置文件中
     * 我们这就简化了，直接写死了。
     * 这个线程池就放10个内存队列。
     * 效果应该是 线程池 启动时候。创建10个内存队列，10个线程
     * 然后每个内存队列都扔到RequestQueue里面去。
     * 然后每个内存队列跟自己的线程绑定在一起。
     *
     */
    private ExecutorService threadPool = Executors.newFixedThreadPool(10);


    public  RequestProcessorThreadPool(){
        // 创建一个内存队列，它是单例的，里面要存放10个含100个请求的队列
        RequestQueue requestQueue = RequestQueue.getInstance();

        for (int i = 0; i < 10; i++) {
            ArrayBlockingQueue<Request> queue = new ArrayBlockingQueue<Request>(100);
            // 内存队列添加含100个请求的队列
            requestQueue.addQueue(queue);
            // 这个 queue 跟 workerThread 是绑定在一起，一个queue对应一个workerThread
            // 然后这个workerThread只有10个线程
            // 这里就是将每个含100个请求的队列 扔进线程池里面去。这样就有了10个线程
            threadPool.submit(new WorkerThread(queue));
        }
    }

    /**
     * 单例有很多种方式去实现，这个是绝对线程安全的一种方式
     *
     * 静态内部类的方式，去初始化单例
     *
     */
    private static class Singleton{

        private static RequestProcessorThreadPool instance;

        static{
            instance = new RequestProcessorThreadPool();
        }

        public static RequestProcessorThreadPool getInstance(){
            return instance;
        }
    }

    /**
     * jvm的机制去保证多线程并发安全
     *
     * 内部类的初始化，一定只会发生一次，不管多少个线程并发去初始化
     *
     * @return
     */
    public static RequestProcessorThreadPool getInstance(){
        return Singleton.getInstance();
    }

    /**
     * 初始化的便捷方法
     */
    public static void init(){
        getInstance();
    }
}
