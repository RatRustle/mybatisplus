package com.rebecca.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.rebecca.mybatisplus.enums.SexEnum;
import lombok.Data;

/**
 * @Author weipeng
 * @Date 2022/9/4 16:23
 */

@Data
//设置实体类所对应的表名
//@TableName("t_user")
public class User {

    //指定为主键,
    //value属性：指定主键的字段
    //type属性：指定主键的生产策略
    @TableId(value = "uid",type = IdType.NONE)
    private Long id;

    //指定属性所对应的字段名
    @TableField("name")
    private String name;

    private Integer age;

    private String email;

    //逻辑删除
    @TableLogic
    private Integer isDeleted;


    private SexEnum sex;

}
