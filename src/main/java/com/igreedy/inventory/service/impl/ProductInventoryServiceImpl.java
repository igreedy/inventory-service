package com.igreedy.inventory.service.impl;

import com.igreedy.inventory.dao.RedisDao;
import com.igreedy.inventory.mapper.ProductInventoryMapper;
import com.igreedy.inventory.model.ProductInventory;
import com.igreedy.inventory.service.ProductInventoryService;

import javax.annotation.Resource;

/**
 * 商品库存service实现类
 * Created by igreedy on 2018/7/23
 */
public class ProductInventoryServiceImpl implements ProductInventoryService {

    @Resource
    private ProductInventoryMapper productInventoryMapper;

    @Resource
    private RedisDao redisDao;

    @Override
    public void updateProductinventory(ProductInventory productInventory) {
        productInventoryMapper.updateProductInventory(productInventory);
    }

    @Override
    public void removeProductInventoryCache(ProductInventory productInventory) {
        String key = "product:inventory:" + productInventory.getProductId();
        redisDao.delete(key);
    }
}
