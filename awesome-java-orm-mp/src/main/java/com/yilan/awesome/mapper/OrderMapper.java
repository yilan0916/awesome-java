package com.yilan.awesome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yilan.awesome.domain.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author： yilan0916
 * @date: 2024/7/9
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
