package com.igreedy.inventory.request;

import com.igreedy.inventory.model.ProductInventory;
import com.igreedy.inventory.service.ProductInventoryService;

/**
 * 重新加载商品库存的缓存，从数据库查出来，然后写到缓存中去
 * Created by igreedy on 2018/7/23
 */
public class ProductInventoryCacheRefreshRequest implements Request {

    /**
     * 商品库存
     */
    private Integer productId;

    /**
     * 商品库存Service
     */
    private ProductInventoryService productInventoryService;

    public ProductInventoryCacheRefreshRequest(Integer productId,
                                               ProductInventoryService productInventoryService) {
        this.productId = productId;
        this.productInventoryService = productInventoryService;
    }

    @Override
    public void process() {
        // 从数据库中查询最新的商品库存数量
        ProductInventory productInventory = productInventoryService.findProductInventory(productId);
        // 将最新的商品库存数量，刷新到redis缓存中去
        productInventoryService.setProductInventoryCache(productInventory);

    }

    @Override
    public Integer getProductId() {
        return productId;
    }
}