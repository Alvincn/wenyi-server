package com.wenyi.wenyi.mapper;

import com.wenyi.wenyi.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 22895
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-04-18 17:37:53
* @Entity com.wenyi.wenyi.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




