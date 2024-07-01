package com.yilan.awesome.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author： yilan0916
 * @date: 2023/11/25
 */
@Getter
@Setter
public class BaseDTO implements Serializable {

    private String createBy;

    private String updateBy;

    private Timestamp createTime;

    private Timestamp updateTime;

}