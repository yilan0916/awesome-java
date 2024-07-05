package com.yilan.awesome.mp.domain.criteria;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
* <p>
*  QueryCriteria
* </p>
*
* @author yilan0916
* @since 2024-07-05
*/
@Data
public class MpTestQueryCriteria implements Serializable {
    private String blurry;

    private List<Timestamp> createTime;

    private Integer pageNum;

    private Integer pageSize;
}