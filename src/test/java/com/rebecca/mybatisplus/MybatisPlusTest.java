package com.rebecca.mybatisplus;

import com.rebecca.mybatisplus.mapper.UserMapper;
import com.rebecca.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author weipeng
 * @Date 2022/9/4 16:34
 */

@SpringBootTest
public class MybatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询所有
     */
    @Test
    public void testSelectList(){
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    /**
     * 新增
     */
    @Test
    public void testInsert(){
        User user = new User();
        user.setName("张三");
        user.setAge(23);
        user.setEmail("123@163.com");
        int result = userMapper.insert(user);
        System.out.println("result:"+result);
        System.out.println("id:"+user.getId());
    }

    /**
     * 删除
     */
    @Test
    public void testDelete(){
        //通过id删除
        //int i = userMapper.deleteById(1566349386898751490L);

        //通过map集合中所设置的条件删除
        //Map<String,Object> map = new HashMap<>();
        //map.put("name", "张三");
        //map.put("age", 23);
        //int i = userMapper.deleteByMap(map);

        //通过多个id来实现批量删除
        List<Long> list = Arrays.asList(1L, 2L, 3L);
        int i = userMapper.deleteBatchIds(list);
        System.out.println("result:"+i);
    }

    /**
     * 修改
     */
    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(4L);
        user.setName("李四");
        user.setEmail("114@qq.com");
        int i = userMapper.updateById(user);
        System.out.println("result:"+i);
    }

    /**
     * 条件查询
     */
    @Test
    public void testSelect(){
        //通过id查询信息
        //User user = userMapper.selectById(1L);
        //System.out.println(user);
        //根据多个id批量查询
        //List<Long> list = Arrays.asList(1L, 2L, 3L);
        //List<User> users = userMapper.selectBatchIds(list);
        //根据map条件集合查询
        //Map<String,Object> map = new HashMap<>();
        //map.put("name","jack");
        //map.put("age",18);
        //List<User> users = userMapper.selectByMap(map);
        Map<String, Object> map = userMapper.selectMapById(1L);
        System.out.println(map);
    }
}
