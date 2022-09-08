package com.rebecca.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

/**
 * description：商品类
 *
 * @author rebecca
 * @date 2022-09-08 16:43
 **/
@Data
public class Product {
    private Long id;
    private String name;
    private Integer price;

    @Version //表示乐观锁版本号字段
    private Integer version;
}
