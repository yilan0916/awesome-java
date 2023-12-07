package com.yilan.awesome.system.mapper;

import com.yilan.awesome.system.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yilan.awesome.system.domain.vo.UserQueryCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author yilan0916
 * @since 2023-11-26
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> findAll(@Param("criteria") UserQueryCriteria criteria);

    Long countAll(@Param("criteria") UserQueryCriteria criteria);

    User findByUsername(@Param("username") String username);

    User findByEmail(@Param("email") String email);

    User findByPhone(@Param("phone") String phone);

    @Select("update sys_user set password = #{password} , pwd_reset_time = #{lastPasswordResetTime} where username = #{username}")
    void updatePass(@Param("username") String username, @Param("password") String password, @Param("lastPasswordResetTime") Timestamp lastPasswordResetTime);

    @Select("update sys_user set email = #{email} where username = #{username}")
    void updateEmail(@Param("username") String username, @Param("email") String email);

    List<User> findByRoleId(@Param("roleId") Long roleId);

    List<User> findByRoleDeptId(@Param("deptId") Long deptId);

    List<User> findByMenuId(@Param("menuId") Long menuId);

    int countByJobs(@Param("jobIds") Set<Long> jobIds);

    int countByDepts(@Param("deptIds") Set<Long> deptIds);

    int countByRoles(@Param("roleIds") Set<Long> roleIds);

    void resetPwd(@Param("userIds") Set<Long> userIds, @Param("pwd") String pwd);
}
