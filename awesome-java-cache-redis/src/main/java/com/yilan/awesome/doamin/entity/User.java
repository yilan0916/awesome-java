package com.yilan.awesome.doamin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author： yilan0916
 * @date: 2024/7/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private Long id;
    private String name;
}
