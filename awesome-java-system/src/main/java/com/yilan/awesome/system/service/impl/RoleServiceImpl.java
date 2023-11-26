package com.yilan.awesome.system.service.impl;

import com.yilan.awesome.system.domain.Role;
import com.yilan.awesome.system.mapper.RoleMapper;
import com.yilan.awesome.system.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author yilan0916
 * @since 2023-11-26
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
