package com.wenyi.wenyi.controller;

import com.wenyi.wenyi.entity.Likes;
import com.wenyi.wenyi.entity.Result;
import com.wenyi.wenyi.entity.User;
import com.wenyi.wenyi.service.LikesService;
import com.wenyi.wenyi.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/likes")
public class LikesController {
    @Autowired
    private LikesService likesService;

    @PostMapping("/addLikes")
    public Result addLikes(@RequestBody Likes likes, @RequestHeader(value = "Authorization", required = false) String token) {
        if(token == null || token.isEmpty()){
            return Result.fail(402, "用户未认证");
        }
        User user = JwtUtil.getUserNameByToken(token);
        likes.setUserId(user.getId());
        Boolean result = likesService.addLikes(likes);
        return result? Result.success(): Result.fail(205, "操作失败");
    }

    @DeleteMapping("/deleteLikes")
    public Result deleteLikes(@RequestBody Likes likes, @RequestHeader(value = "Authorization", required = false) String token) {
        if(token == null || token.isEmpty()){
            return Result.fail(402, "用户未认证");
        }
        User user = JwtUtil.getUserNameByToken(token);
        likes.setUserId(user.getId());
        Boolean result = likesService.deleteLikes(likes);
        return result? Result.success(): Result.fail(205, "操作失败");
    }

    @GetMapping("/getLikes")
    public Result getLikes(Integer userId) {
        System.out.println(userId);
        List<Likes> likes = likesService.getLikes(userId);
        return Result.success(likes);
    }

    @GetMapping("/userLikePost")
    public Result userSavePost(Likes likes, @RequestHeader(value = "Authorization", required = false) String token) {
        if(token == null || token.isEmpty()){
            return Result.fail(402, "用户未认证");
        }
        User user = JwtUtil.getUserNameByToken(token);
        likes.setUserId(user.getId());
        Boolean result = likesService.userLikesPost(likes);
        return Result.success(result);
    }
}
