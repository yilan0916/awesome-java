package com.yilan.awesome.domain.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * @author： yilan0916
 * @date: 2024/6/26
 */
@Data
public class UserDTO {

    private Long id;

    @NotBlank
    @Size(min = 2, max = 20, message = "用户名长度在2-20之间")
    private String username;

    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, max = 20, message = "密码长度在6-20之间")
    private String password;

    @Range(min = 1, max = 100, message = "年龄在1-100之间")
    private Integer age;

    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$", message = "手机号码格式错误")
    private String phone;

    @Email(regexp = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$")
    @NotNull
    private String email;

    @NotNull(message = "生日不能为空")
    @Past(message = "生日必须是一个过去的时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDay;


}