package com.yilan.awesome.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Accessors(chain = true)
@Data
@TableName("sys_order")
public class Order implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("status")
    private Status status;


    @Getter
    @AllArgsConstructor
    public enum Status {
        /**
         * 订单状态
         */
        CANCELED(0, "已取消"),
        WAIT_PAY(1, "等待付款"),
        WAIT_TRANSFER(2, "等待转账"),
        WAIT_RECEIVE(3, "等待收货"),
        RECEIVE(4, "已收货"),
        FINISHED(5, "已完成");

        @EnumValue
        private final Integer value;
        @JsonValue
        private final String description;
    }
}
