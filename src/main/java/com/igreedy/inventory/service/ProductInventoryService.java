package com.igreedy.inventory.service;

import com.igreedy.inventory.model.ProductInventory;

/**
 * 商品库存Service接口
 * Created by igreedy on 2018/7/23
 */
public interface ProductInventoryService {

    /**
     * 更新商品库存
     * @param productInventory 商品库存说
     */
    void updateProductinventory(ProductInventory productInventory);

    /**
     * 删除redis中的商品库存的缓存
     * @param productInventory
     */
    void removeProductInventoryCache(ProductInventory productInventory);

    /**
     * 根据商品id查询商品库存
     * @param productId 商品ID
     * @return 商品库存
     */
    ProductInventory findProductInventory(Integer productId);

    /**
     * 设置商品库存的缓存
     * @param productInventory 商品库存
     */
    void setProductInventoryCache(ProductInventory productInventory);

    /**
     * 获取商品库存的缓存
     * @param productId
     * @return
     */
    ProductInventory getProductInventoryCache(Integer productId);
}
