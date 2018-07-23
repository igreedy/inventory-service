package com.igreedy.inventory.dao.impl;

import com.igreedy.inventory.dao.RedisDao;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;

/**
 * Created by igreedy on 2018/7/22
 */

@Repository("redisDao")
public class RedisDaoImpl implements RedisDao {

    @Resource
    private JedisCluster jedisCluster;

    @Override
    public void set(String key, String value) {
        jedisCluster.set(key, value);
    }

    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }

    @Override
    public void delete(String key) {
        jedisCluster.del(key);
    }
}
