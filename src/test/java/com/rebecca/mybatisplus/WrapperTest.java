package com.rebecca.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.rebecca.mybatisplus.mapper.UserMapper;
import com.rebecca.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @Author weipeng
 * @Date 2022/9/5 21:34
 */
@SpringBootTest
public class WrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test01() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "a").between("age", 20, 30).isNotNull("email");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test02() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age").orderByAsc("uid");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test03() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        int num = userMapper.delete(queryWrapper);
        System.out.println("num:" + num);
    }

    @Test
    public void test04() {
        //将年龄大于20并且用户名中包含a，或邮箱为null的用户修改
        //UPDATE t_user SET name=?, email=? WHERE is_deleted=0 AND (age > ? AND name LIKE ? OR email IS NULL)
        User user = new User();
        user.setName("小明");
        user.setEmail("111@163.com");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 20).like("name", "a").or().isNull("email");
        int i = userMapper.update(user, queryWrapper);
        System.out.println("num:" + i);
    }

    @Test
    public void test05() {
        //将用户名中包含a并且（年龄大于20或者邮箱为null）的用户修改
        //UPDATE t_user SET name=?, email=? WHERE is_deleted=0 AND (name LIKE ? AND (age > ? OR email IS NULL))
        //lambda表达式中的条件优先执行
        User user = new User();
        user.setName("小红");
        user.setEmail("111@163.com");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "a").and(i -> i.gt("age", 20).or().isNull("email"));
        int i = userMapper.update(user, queryWrapper);
        System.out.println("num:" + i);
    }

    /**
     * test06 组装select
     */
    @Test
    public void test06() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("uid", "age", "name");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }


    /**
     * test07 子查询
     */
    @Test
    public void test07() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("uid", "select uid from t_user where uid <= 100");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * updateWrapper
     * test08
     */
    @Test
    public void test08() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.like("name", "a").and(i -> i.gt("age", 20).or().isNull("email"));
        updateWrapper.set("name", "张三").set("email", "123@qq.com");
        int num = userMapper.update(null, updateWrapper);
        System.out.println("num:" + num);
    }

    /**
     * test09 condition组装条件
     */
    @Test
    public void test09() {
        String name = "";
        Integer beginAge = 15;
        Integer endAge = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(name), "name", name).ge(beginAge != null, "age", beginAge).le(endAge != null, "age", endAge);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * test10 LambdaQueryWrapper
     */
    @Test
    public void test10() {
        String name = "";
        Integer beginAge = 15;
        Integer endAge = 30;
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(name), User::getName, name).ge(beginAge != null, User::getAge, beginAge).le(endAge != null, User::getAge, endAge);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * test11
     * LambdaUpdateWrapper
     */
    @Test
    public void test11(){
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.like(User::getName, "a").and(i -> i.gt(User::getAge, 20).or().isNull(User::getEmail));
        updateWrapper.set(User::getName, "张三").set(User::getEmail, "123@qq.com");
        int num = userMapper.update(null, updateWrapper);
        System.out.println("num:" + num);
    }
}
