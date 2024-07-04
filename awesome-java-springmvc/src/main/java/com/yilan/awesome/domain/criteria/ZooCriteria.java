package com.yilan.awesome.domain.criteria;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author： yilan0916
 * @date: 2024/6/27
 */
@Data
@Schema(description = "任务模块-统一查询模型")
public class ZooCriteria {

    @Schema(description = "分类目录：所有任务、我创建的、我参与的")
    private String category;
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "模糊查询")
    private String blurry;
    @Schema(description = "页码")
    private Integer pageNum;
    @Schema(description = "页大小")
    private Integer pageSize;
}
