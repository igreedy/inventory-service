package com.igreedy.inventory.request;


import com.igreedy.inventory.model.ProductInventory;
import com.igreedy.inventory.service.ProductInventoryService;

/**
 * 比如说一个商品发生了交易，那么就要修改这个商品对应的库存
 * <p>
 * 此时就会发送请求过来，要求修改库存，那么这个可能就是所谓的 data update request
 * 数据更新请求
 * <p>
 * cache aside pattern
 * <p>
 * （1）删除缓存
 * (2) 更新数据库
 * <p>
 * Created by igreedy on 2018/7/23
 */
public class ProductInventoryDBUpdateRequest implements Request {

    /**
     * 商品库存
     */
    private ProductInventory productInventory;

    /**
     * 商品库存Service
     */
    private ProductInventoryService productInventoryService;

    public ProductInventoryDBUpdateRequest(ProductInventory productInventory,
                                           ProductInventoryService productInventoryService) {
        this.productInventory = productInventory;
        this.productInventoryService = productInventoryService;
    }

    @Override
    public void process() {
        // 删除redis中的缓存
        productInventoryService.removeProductInventoryCache(productInventory);
        // 修改数据库中的库存
        productInventoryService.updateProductinventory(productInventory);
    }

    /**
     * 获取商品id
     * @return
     */
    @Override
    public Integer getProductId() {
        return productInventory.getProductId();
    }

}
