package com.yilan.awesome.service;

import com.yilan.awesome.doamin.entity.User;

/**
 * @author： yilan0916
 * @date: 2024/7/7
 */
public interface UserService {


    /**
     * 获取用户
     *
     * @param id key值
     * @return 返回结果
     */
    User get(Long id);

    User getWithTTL(long id);

    /**
     * 保存或修改用户
     *
     * @param user 用户对象
     * @return 操作结果
     */
    User saveOrUpdate(User user);

    /**
     * 删除
     *
     * @param id key值
     */
    void delete(Long id);
}
