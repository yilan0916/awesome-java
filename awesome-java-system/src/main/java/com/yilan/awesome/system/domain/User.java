package com.yilan.awesome.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yilan.awesome.base.BaseEntity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author yilan0916
 * @since 2023-11-26
 */
@Getter
@Setter
@TableName("sys_user")
@ApiModel(value = "User对象", description = "系统用户")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    @ApiModelProperty("部门ID")
    private Long deptId;

    @ApiModelProperty("部门")
    @TableField(exist = false)
    private Dept dept;

    @ApiModelProperty("用户角色")
    @TableField(exist = false)
    private Set<Role> roles;

    @ApiModelProperty("用户岗位")
    @TableField(exist = false)
    private Set<Job> jobs;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty(value = "头像地址", hidden = true)
    private String avatarName;

    @ApiModelProperty(value = "头像真实路径", hidden = true)
    private String avatarPath;

    @ApiModelProperty(value = "修改密码时间", hidden = true)
    private LocalDateTime pwdResetTime;

    @ApiModelProperty(value = "是否为admin账号", hidden = true)
    private Boolean isAdmin;

    @ApiModelProperty("是否启用")
    private Boolean isEnabled;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username);
    }
}
