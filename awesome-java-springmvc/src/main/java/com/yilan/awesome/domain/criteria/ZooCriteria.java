package com.yilan.awesome.domain.criteria;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author： yilan0916
 * @date: 2024/6/27
 */
@Data
@ApiModel("任务模块-统一查询模型")
public class ZooCriteria {

    @ApiModelProperty("分类目录：所有任务、我创建的、我参与的")
    private String category;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("模糊查询")
    private String blurry;
    @ApiModelProperty("页码")
    private Integer pageNum;
    @ApiModelProperty("页大小")
    private Integer pageSize;
}
