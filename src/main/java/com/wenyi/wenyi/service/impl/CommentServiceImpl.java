package com.wenyi.wenyi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenyi.wenyi.entity.Comment;
import com.wenyi.wenyi.service.CommentService;
import com.wenyi.wenyi.mapper.CommentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 22895
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2024-04-30 00:00:49
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

    private final UserServiceImpl userServiceImpl;

    public CommentServiceImpl(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public Boolean sendComment(Comment comment) {
        return this.save(comment);
    }

    @Override
    public List<Comment> getCommentList(Integer postId) {
        List<Comment> comments = this.list(new QueryWrapper<Comment>().eq("post_id", postId));
        comments.stream().forEach(v -> {
           v.setUser(userServiceImpl.clearUser(userServiceImpl.getById(v.getUserId())));
        });
        return comments;
    }
}




