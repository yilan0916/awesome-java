package com.yilan.awesome.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author： yilan0916
 * @date: 2024/6/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("sys_user")
public class User {

    @TableId(value = "user_id",type = IdType.ASSIGN_ID)
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;
    @TableLogic
    private Boolean isDeleted;
}
