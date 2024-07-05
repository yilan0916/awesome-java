package com.yilan.awesome.mp.domain.dto;

import java.io.Serializable;

import lombok.Data;

/**
* <p>
*  DTO
* </p>
*
* @author yilan0916
* @since 2024-07-05
*/
@Data
public class MpTestDTO {

    private Long id;

    private String name;

    private String address;

    private Boolean isEnabled;
}
