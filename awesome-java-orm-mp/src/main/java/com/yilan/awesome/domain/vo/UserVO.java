package com.yilan.awesome.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yilan.awesome.domain.entity.Dept;
import com.yilan.awesome.domain.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @author： yilan0916
 * @date: 2024/7/5
 */
@Data
@Schema(name = "User", description = "系统用户")
public class UserVO {

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
