package com.yilan.awesome.mp.domain.vo;

import java.io.Serializable;

import lombok.Data;

/**
* <p>
*  VO
* </p>
*
* @author yilan0916
* @since 2024-07-05
*/
@Data
public class MpTestVO {

    private Long id;

    private String name;

    private String address;

    private Boolean isEnabled;
}
