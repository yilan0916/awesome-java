package com.yilan.awesome.system.service.impl;

import com.yilan.awesome.system.domain.User;
import com.yilan.awesome.system.mapper.UserMapper;
import com.yilan.awesome.system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author yilan0916
 * @since 2023-11-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
