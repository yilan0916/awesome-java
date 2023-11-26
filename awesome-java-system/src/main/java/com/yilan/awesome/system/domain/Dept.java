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
 * 部门
 * </p>
 *
 * @author yilan0916
 * @since 2023-11-26
 */
@Getter
@Setter
@TableName("sys_dept")
@ApiModel(value = "Dept对象", description = "部门")
public class Dept extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("部门ID")
    @TableId(value = "dept_id", type = IdType.AUTO)
    private Long deptId;

    @ApiModelProperty("上级部门")
    private Long pid;

    @ApiModelProperty("子部门数量")
    private Integer subCount;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("部门排序")
    private Integer deptSort;

    @ApiModelProperty("是否启用")
    private Boolean isEnabled;
}
