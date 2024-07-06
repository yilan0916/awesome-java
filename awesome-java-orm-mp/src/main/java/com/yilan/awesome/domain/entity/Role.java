package com.yilan.awesome.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yilan.awesome.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author： yilan0916
 * @date: 2024/7/6
 */
@Data
@TableName("sys_role")
@Schema(name = "Role", description = "角色表")
public class Role extends BaseEntity implements Serializable {

    @Schema(description = "角色ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @Schema(description = "级别")
    @TableField("level")
    private Integer level;

    @Schema(description = "描述")
    @TableField("description")
    private String description;

    @TableField("data_scope")
    private String dataScope;
}
