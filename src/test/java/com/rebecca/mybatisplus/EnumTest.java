package com.rebecca.mybatisplus;

import com.rebecca.mybatisplus.enums.SexEnum;
import com.rebecca.mybatisplus.mapper.UserMapper;
import com.rebecca.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * description：TODO
 *
 * @author rebecca
 * @date 2022-09-08 17:15
 **/
@SpringBootTest
public class EnumTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        User user = new User();
        user.setId(99L);
        user.setName("瑞贝卡");
        user.setAge(20);
        user.setSex(SexEnum.FEMALE);
        int result = userMapper.insert(user);
        System.out.println("result:"+result);
    }
}
