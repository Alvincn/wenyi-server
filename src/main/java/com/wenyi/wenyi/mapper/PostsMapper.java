package com.wenyi.wenyi.mapper;

import com.wenyi.wenyi.entity.Posts;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 22895
* @description 针对表【posts】的数据库操作Mapper
* @createDate 2024-04-22 15:19:16
* @Entity com.wenyi.wenyi.entity.Posts
*/
@Mapper
public interface PostsMapper extends BaseMapper<Posts> {

}




