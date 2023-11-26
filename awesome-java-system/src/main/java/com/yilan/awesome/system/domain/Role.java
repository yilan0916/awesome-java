package com.yilan.awesome.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yilan.awesome.base.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author yilan0916
 * @since 2023-11-26
 */
@Getter
@Setter
@TableName("sys_role")
@ApiModel(value = "Role对象", description = "角色表")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色ID")
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    private String name;

    @ApiModelProperty("级别")
    private Integer level;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty(value = "数据权限，全部 、 本级 、 自定义")
    private String dataScope;
}
