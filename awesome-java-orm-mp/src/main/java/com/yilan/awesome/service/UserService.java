package com.yilan.awesome.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yilan.awesome.domain.criteria.UserCriteria;
import com.yilan.awesome.domain.entity.User;

import java.util.List;

/**
 * @author： yilan0916
 * @date: 2024/6/29
 */
public interface UserService {

    User getById(Long id);
    List<User> getAll();
    IPage<User> page(UserCriteria criteria);
    User save(User user);
    User create(User user);
    User update(User user);
    Boolean deleteById(Long id);
    Boolean saveBatch(List<User> list);

}
