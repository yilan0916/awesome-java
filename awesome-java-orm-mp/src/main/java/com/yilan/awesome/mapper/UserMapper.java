package com.yilan.awesome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yilan.awesome.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author： yilan0916
 * @date: 2024/6/29
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
