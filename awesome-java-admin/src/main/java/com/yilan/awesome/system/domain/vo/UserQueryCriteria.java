package com.yilan.awesome.system.domain.vo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author： yilan0916
 * @date : 2023/11/26
 */
@Data
public class UserQueryCriteria {

    private Long userId;

    private Set<Long> deptIds = new HashSet<>();

    private String blurry;

    private Boolean isEnabled;

    private Long deptId;

    private List<Timestamp> createTime;

    private Long offset;

    private Long size;
}
