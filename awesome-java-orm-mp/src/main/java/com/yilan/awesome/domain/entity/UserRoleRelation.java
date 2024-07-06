package com.yilan.awesome.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author： yilan0916
 * @date: 2024/7/6
 */
@Data
@TableName("sys_users_roles")
@Schema(name = "UserRoleRelation", description = "系统用户")
public class UserRoleRelation {

    private Long userId;
    private Long roleId;
}
