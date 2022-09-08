package com.rebecca.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rebecca.mybatisplus.mapper.ProductMapper;
import com.rebecca.mybatisplus.mapper.UserMapper;
import com.rebecca.mybatisplus.pojo.Product;
import com.rebecca.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * description：分页插件测试
 *
 * @author rebecca
 * @date 2022-09-08 11:22
 **/
@SpringBootTest
public class PluginsTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductMapper productMapper;

    /**
     * 分页查询
     */
    @Test
    public void testPage(){
        Page<User> page = new Page<>(2,3);
        userMapper.selectPage(page, null);
        System.out.println(page);
        System.out.println(page.getRecords());
        System.out.println(page.getPages());
        System.out.println(page.getTotal());
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());
    }

    /**
     * 自定义分页查询
     */
    @Test
    public void testPageVo(){
        Page<User> page = new Page<>(2,3);
        userMapper.selectPageVo(page,20);
        System.out.println(page.getRecords());
        System.out.println(page.getPages());
        System.out.println(page.getTotal());
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());
    }

    /**
     * 测试product01
     */
    @Test
    public void testProduct01(){
        //a查询价格
        Product product1 = productMapper.selectById(1);
        System.out.println("a查询的商品价格："+product1.getPrice());
        //b查询价格
        Product product2 = productMapper.selectById(1);
        System.out.println("b查询的商品价格："+product2.getPrice());
        //a将商品价格+50
        product1.setPrice(product1.getPrice()+50);
        productMapper.updateById(product1);
        //b将商品价格-30
        product2.setPrice(product2.getPrice()-30);
        int result = productMapper.updateById(product2);
        if (result==0){
            //操作失败，重试
            Product product2new = productMapper.selectById(1);
            product2new.setPrice(product2new.getPrice()-30);
            productMapper.updateById(product2new);
        }
        //c查询商品价格
        Product product3 = productMapper.selectById(1);
        System.out.println("c查询的商品价格："+product3.getPrice());
    }
}
