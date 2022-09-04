package com.rebecca.mybatisplus.pojo;

import lombok.Data;

/**
 * @Author weipeng
 * @Date 2022/9/4 16:23
 */

@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
