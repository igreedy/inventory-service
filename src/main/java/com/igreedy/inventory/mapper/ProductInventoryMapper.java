package com.igreedy.inventory.mapper;

import com.igreedy.inventory.model.ProductInventory;

/**
 * 库存数量Mapper
 * Created by igreedy on 2018/7/23
 */
public interface ProductInventoryMapper {

    /**
     * 更新库存数量
     * @param productInventory 商品库存
     */
    void updateProductInventory(ProductInventory productInventory);
}
