package com.wenyi.wenyi.controller;

import com.wenyi.wenyi.entity.Comment;
import com.wenyi.wenyi.entity.Result;
import com.wenyi.wenyi.entity.User;
import com.wenyi.wenyi.service.CommentService;
import com.wenyi.wenyi.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/getComment")
    public Result getComment(Integer postId) {
        List<Comment> commentList = commentService.getCommentList(postId);
        return Result.success(commentList);
    }

    @PostMapping("/addComment")
    public Result addComment(@RequestHeader(value = "Authorization", required = false) String token, @RequestBody Comment comment) {
        if (token == null || token.isEmpty()) {
            return Result.fail(402, "用户未认证");
        }
        User user = JwtUtil.getUserNameByToken(token);
        comment.setUserId(user.getId());
        Boolean flag = commentService.sendComment(comment);
        return flag ? Result.success() : Result.fail();
    }
}
