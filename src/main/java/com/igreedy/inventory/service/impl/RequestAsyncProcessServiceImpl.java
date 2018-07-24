package com.igreedy.inventory.service.impl;

import com.igreedy.inventory.request.Request;
import com.igreedy.inventory.request.RequestQueue;
import com.igreedy.inventory.service.RequestAsyncProcessService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 请求异步处理的service实现
 * <p>
 * Created by igreedy on 2018/7/23
 */
@Service("requestAsyncProcessService")
public class RequestAsyncProcessServiceImpl implements RequestAsyncProcessService {

    /**
     * 将接收到的请求加入到对应的内存队列中去
     * @param request
     */
    @Override
    public void process(Request request) {
        try {
            // 做请求的路由，根据每个请求的商品id，路由到对应的内存队列中去
            ArrayBlockingQueue<Request> queue = getRoutingQueue(request.getProductId());

            //将请求放入对应的队列中，完成路由操作
            queue.put(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取路由到的内存队列
     *
     * @param productId 商品id
     * @return 内存队列
     */
    private ArrayBlockingQueue getRoutingQueue(Integer productId) {
        RequestQueue requestQueue = RequestQueue.getInstance();

        // 先获取productId的hash值
        String key = String.valueOf(productId);
        int h;
        int hash = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);

        /**
         * 对hash值取模，将hash值路由到指定的内存队列中
         * 假设内存队列大小是10，那么用内存队列的数量对hash值取模之后
         * 结果一定是在0~9之间，所以任何一个商品id都会被路由到同一个内存队列中去的
         */
        int index = (requestQueue.queueSize() - 1) & hash;

        return requestQueue.getQueue(index);
    }

}
