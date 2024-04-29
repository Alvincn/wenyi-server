package com.wenyi.wenyi.service;

import com.wenyi.wenyi.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 22895
* @description 针对表【comment】的数据库操作Service
* @createDate 2024-04-30 00:00:50
*/
public interface CommentService extends IService<Comment> {
    // 发布评论
    Boolean sendComment(Comment comment);

    // 获取帖子下全部评论
    List<Comment> getCommentList(Integer postId);
}
