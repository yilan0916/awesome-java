package com.yilan.awesome.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yilan.awesome.base.BaseEntity;
import com.yilan.awesome.domain.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 部门 Entity
 * </p>
 *
 * @author yilan0916
 * @since 2024-07-05
 */
@Data
@Schema(name = "Dept", description = "部门")
public class DeptVO extends BaseEntity {

    @Schema(description = "部门ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "上级部门")
    @TableField("pid")
    private Long pid;

    @Schema(description = "子部门数量")
    @TableField("sub_count")
    private Integer subCount;

    @Schema(description = "名称")
    @TableField("name")
    private String name;

    @Schema(description = "部门成员")
    @TableField(exist = false)
    private List<User> userList;

    @Schema(description = "部门排序")
    @TableField("dept_sort")
    private Integer deptSort;

    @Schema(description = "是否启用")
    @TableField("is_enabled")
    private Boolean isEnabled;
}
