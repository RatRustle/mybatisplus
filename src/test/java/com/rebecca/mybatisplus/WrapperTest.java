package com.rebecca.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rebecca.mybatisplus.mapper.UserMapper;
import com.rebecca.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author weipeng
 * @Date 2022/9/5 21:34
 */
@SpringBootTest
public class WrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test01(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "a").between("age", 20, 30).isNotNull("email");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test02(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age").orderByAsc("uid");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test03(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        int num = userMapper.delete(queryWrapper);
        System.out.println("num:"+num);
    }

    @Test
    public void test04(){
        User user = new User();
        user.setName("小明");
        user.setEmail("111@163.com");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 20).like("name", "a").or().isNull("email");
        int i = userMapper.update(user, queryWrapper);
        System.out.println("num:"+i);
    }
}
