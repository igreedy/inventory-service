package com.igreedy.inventory.service;

import com.igreedy.inventory.request.Request;

/**
 * 请求异步执行的service
 *
 * 主要是做请求路由，和后面一些优化的操作
 *
 * Created by igreedy on 2018/7/23
 */
public interface RequestAsyncProcessService {

    void process(Request request);
}
