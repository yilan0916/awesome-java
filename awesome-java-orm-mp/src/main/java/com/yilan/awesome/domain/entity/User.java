package com.yilan.awesome.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yilan.awesome.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 系统用户 Entity
 * </p>
 *
 * @author yilan0916
 * @since 2024-07-05
 */
@Data
@TableName("sys_user")
@Schema(name = "User", description = "系统用户")
public class User extends BaseEntity implements Serializable {

    @Schema(description = "用户ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "部门ID")
    @TableField("dept_id")
    private Long deptId;

    @Schema(description = "用户部门")
    @TableField(exist = false)
    private Dept dept;

    @TableField(exist = false)
    @Schema(description = "用户角色")
    private List<Role> roles;

//    @TableField(exist = false)
//    @Schema(description = "用户岗位")
//    private Set<Job> jobs;

    @Schema(description = "用户名")
    @TableField("username")
    private String username;

    @Schema(description = "密码")
    @TableField("password")
    private String password;

    @Schema(description = "昵称")
    @TableField("nick_name")
    private String nickName;

    @Schema(description = "性别")
    @TableField("gender")
    private String gender;

    @Schema(description = "手机号码")
    @TableField("phone")
    private String phone;

    @Schema(description = "邮箱")
    @TableField("email")
    private String email;

    @Schema(description = "头像地址")
    @TableField("avatar_name")
    private String avatarName;

    @Schema(description = "头像真实路径")
    @TableField("avatar_path")
    private String avatarPath;

    @Schema(description = "修改密码时间")
    @TableField("pwd_reset_time")
    private LocalDateTime pwdResetTime;

    @Schema(description = "是否为admin账号")
    @TableField("is_admin")
    private Boolean isAdmin;

    @Schema(description = "是否启用")
    @TableField("is_enabled")
    private Boolean isEnabled;
}
