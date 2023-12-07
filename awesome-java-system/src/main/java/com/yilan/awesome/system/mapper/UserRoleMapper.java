package com.yilan.awesome.system.mapper;

import com.yilan.awesome.system.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @author： yilan0916
 * @date : 2023/12/1
 */
@Mapper
public interface UserRoleMapper {

    void insertData(@Param("userId") Long userId, @Param("roles") Set<Role> roles);

    void deleteByUserId(@Param("userId") Long userId);

    void deleteByUserIds(@Param("userIds") Set<Long> userIds);
}
