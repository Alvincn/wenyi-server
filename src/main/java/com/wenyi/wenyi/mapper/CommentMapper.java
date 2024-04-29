package com.wenyi.wenyi.mapper;

import com.wenyi.wenyi.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 22895
* @description 针对表【comment】的数据库操作Mapper
* @createDate 2024-04-30 00:00:49
* @Entity com.wenyi.wenyi.entity.Comment
*/
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}




