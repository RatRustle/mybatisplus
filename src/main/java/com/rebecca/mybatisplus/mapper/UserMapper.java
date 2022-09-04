package com.rebecca.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rebecca.mybatisplus.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author weipeng
 * @Date 2022/9/4 16:30
 */
@Repository //标注为持久层主键
public interface UserMapper extends BaseMapper<User> {

}
