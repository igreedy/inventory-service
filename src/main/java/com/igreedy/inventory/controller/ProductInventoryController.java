package com.igreedy.inventory.controller;

import com.igreedy.inventory.model.ProductInventory;
import com.igreedy.inventory.request.ProductInventoryCacheRefreshRequest;
import com.igreedy.inventory.request.ProductInventoryDBUpdateRequest;
import com.igreedy.inventory.request.Request;
import com.igreedy.inventory.service.ProductInventoryService;
import com.igreedy.inventory.service.RequestAsyncProcessService;
import com.igreedy.inventory.vo.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 商品库存Controller
 * Created by igreedy on 2018/7/23
 */
public class ProductInventoryController {

    @Resource
    private RequestAsyncProcessService requestAsyncProcessService;

    @Resource
    private ProductInventoryService productInventoryService;

    /**
     * 获取商品库存
     *
     * @param productId
     * @return
     */
    @RequestMapping("/getProductinventory")
    @ResponseBody
    public ProductInventory getProductInventory(Integer productId) {
        ProductInventory productInventory = null;
        try {
            Request request = new ProductInventoryCacheRefreshRequest(
                    productId, productInventoryService);
            requestAsyncProcessService.process(request);

            /**
             * 将请求扔给service异步处理以后，就需要while(true)一会儿，在这里hang住。
             * 去尝试等待前面有商品库存更新的操作，同时缓存刷新的操作，将最新的数据刷新
             * 到缓存中
             */
            long startTime = System.currentTimeMillis();
            long waitTime = 0L;
            long endTime = 0L;

            while (true) {
                // 等待最多hang200毫秒
                if (waitTime > 200) {
                    break;
                }
                // 尝试去redis中读取一次商品库存的缓存数据
                productInventory = productInventoryService.getProductInventoryCache(productId);

                // 如果读取到了结果，那么就返回
                if (productInventory != null) {
                    return productInventory;
                }

                // 如果没有读取到结果，那么就等待一段时间
                else {
                    Thread.sleep(20);
                    endTime = System.currentTimeMillis();
                    waitTime = endTime - startTime;
                }
            }
            // 等待200毫秒 依然没从缓存中读取数据
            // 那么就直接尝试从数据库中读取数据
            productInventory = productInventoryService.findProductInventory(productId);
            if (productInventory != null) {
                return productInventory;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ProductInventory(productId, -1L);
    }

    /**
     * 更新商品库存
     *
     * @param productInventory
     * @return
     */
    @RequestMapping("/updateProductinventory")
    @ResponseBody
    public Response updateProductInventory(ProductInventory productInventory) {
        Response response = null;
        try {
            Request request = new ProductInventoryDBUpdateRequest(
                    productInventory, productInventoryService);
            requestAsyncProcessService.process(request);
            response = new Response(Response.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response = new Response(Response.FAILURE);
        }
        return response;
    }
}
