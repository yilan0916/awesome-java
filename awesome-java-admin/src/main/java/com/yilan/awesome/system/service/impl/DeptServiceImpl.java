package com.yilan.awesome.system.service.impl;

import com.yilan.awesome.system.domain.Dept;
import com.yilan.awesome.system.mapper.DeptMapper;
import com.yilan.awesome.system.service.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门 服务实现类
 * </p>
 *
 * @author yilan0916
 * @since 2023-11-26
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

}
