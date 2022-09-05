package com.rebecca.mybatisplus;

import com.rebecca.mybatisplus.pojo.User;
import com.rebecca.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testSelect(){
        long count = userService.count();
        System.out.println("count:"+count);
    }

    @Test
    public void batchInsert(){
        List<User> userList = new ArrayList<>();
        for (int i = 0;i < 10;i++){
            User user = new User();
            user.setName("rebecca"+i);
            user.setAge(15+i);
            userList.add(user);
        }
        userService.saveBatch(userList);
    }
}
