package com.yilan.awesome.domain.criteria;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author： yilan0916
 * @date: 2024/6/29
 */
@Data
public class DeptCriteria implements Serializable {

    private String blurry;

    private List<Timestamp> createTime;

    private Integer pageNum;

    private Integer pageSize;
}
