package com.rebecca.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rebecca.mybatisplus.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @Author weipeng
 * @Date 2022/9/4 16:30
 */
@Repository //标注为持久层主键
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据id查询用户为map集合
     * @param id
     * @return
     */
    Map<String,Object> selectMapById(Long id);

    /**
     * 根据年龄查询用户信息并分页
     *
     * @param page 页面
     * @param age  年龄
     * @return {@link Page}<{@link User}>
     */
    Page<User> selectPageVo(@Param("page") Page<User> page,@Param("age") Integer age);
}
