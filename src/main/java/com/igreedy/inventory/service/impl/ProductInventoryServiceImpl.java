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

    /**
     * 根据商品id查询商品库存
     *
     * @param productId 商品ID
     * @return 商品库存
     */
    @Override
    public ProductInventory findProductInventory(Integer productId) {
        return productInventoryMapper.findProductInventory(productId);
    }

    /**
     * 设置商品库存的缓存
     *
     * @param productInventory 商品库存
     */
    @Override
    public void setProductInventoryCache(ProductInventory productInventory) {
        String key = "product:inventory:" + productInventory.getProductId();
        redisDao.set(key, String.valueOf(productInventory.getInventoryCnt()));
    }

    /**
     * 获取商品库存的缓存
     *
     * @param productId
     * @return
     */
    @Override
    public ProductInventory getProductInventoryCache(Integer productId) {
        Long inventoryCnt = 0L;
        String key = "product:inventory:" + productId;
        String result = redisDao.get(key);
        if (result != null && !"".equals(result)) {
            try {
                inventoryCnt = Long.valueOf(result);
                return new ProductInventory(productId, inventoryCnt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
