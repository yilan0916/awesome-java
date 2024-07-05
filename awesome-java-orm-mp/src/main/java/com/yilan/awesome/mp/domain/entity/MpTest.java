package com.yilan.awesome.mp.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yilan.awesome.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 *  Entity
 * </p>
 *
 * @author yilan0916
 * @since 2024-07-05
 */
@Data
@TableName("mp_test")
@Schema(name = "MpTest", description = "")
public class MpTest extends BaseEntity {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("address")
    private String address;

    @TableField("is_enabled")
    private Boolean isEnabled;
}
