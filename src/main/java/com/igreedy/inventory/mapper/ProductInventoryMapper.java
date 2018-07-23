package com.igreedy.inventory.mapper;

import com.igreedy.inventory.model.ProductInventory;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 根据商品id查询商品库存信息
     * @param productId 商品id
     * @return 商品库存信息
     */
    ProductInventory findProductInventory(@Param("productId") Integer productId);
}
