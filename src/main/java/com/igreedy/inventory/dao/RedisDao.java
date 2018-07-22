package com.igreedy.inventory.dao;

/**
 * reids本身有各种各样的api和功能
 *
 * Created by igreedy on 2018/7/22
 */
public interface RedisDao {

    void set(String key, String value);

    String get(String key);
}
