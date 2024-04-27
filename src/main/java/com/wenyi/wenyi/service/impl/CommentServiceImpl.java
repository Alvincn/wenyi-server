package com.wenyi.wenyi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenyi.wenyi.entity.Comment;
import com.wenyi.wenyi.service.CommentService;
import com.wenyi.wenyi.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author 22895
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2024-04-25 22:52:07
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

    @Override
    public Boolean addComment(Comment comment) {
        return null;
    }

    @Override
    public Boolean deleteComment(Integer id) {
        return null;
    }

    @Override
    public Boolean updateComment(Comment comment) {
        return null;
    }

    @Override
    public Boolean likeComment(Integer id) {
        return null;
    }

    @Override
    public Boolean unlikeComment(Integer id) {
        return null;
    }

    @Override
    public Comment getCommentByPostId(Integer postId) {
        return null;
    }
}




