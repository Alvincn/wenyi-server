package com.wenyi.wenyi.controller;

import com.wenyi.wenyi.entity.Collection;
import com.wenyi.wenyi.entity.Result;
import com.wenyi.wenyi.entity.User;
import com.wenyi.wenyi.service.CollectionService;
import com.wenyi.wenyi.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @PostMapping("/addCollection")
    public Result addCollection(@RequestBody Collection collection, @RequestHeader(value = "Authorization", required = false) String token) {
        if(token == null || token.isEmpty()){
            return Result.fail(402, "用户未认证");
        }
        User user = JwtUtil.getUserNameByToken(token);
        collection.setUserId(user.getId());
        Boolean result = collectionService.addCollection(collection);
        return result? Result.success(): Result.fail(205, "操作失败");
    }

    @DeleteMapping("/deleteCollection")
    public Result deleteCollection(@RequestBody Collection collection, @RequestHeader(value = "Authorization", required = false) String token) {
        if(token == null || token.isEmpty()){
            return Result.fail(402, "用户未认证");
        }
        User user = JwtUtil.getUserNameByToken(token);
        collection.setUserId(user.getId());
        Boolean result = collectionService.deleteCollection(collection);
        return result? Result.success(): Result.fail(205, "操作失败");
    }

    @GetMapping("/getCollections")
    public Result getCollections(Integer userId) {
        List<Collection> collections = collectionService.getCollections(userId);
        return Result.success(collections);
    }

    @GetMapping("/userSavePost")
    public Result userSavePost(Collection collection, @RequestHeader(value = "Authorization", required = false) String token) {
        if(token == null || token.isEmpty()){
            return Result.fail(402, "用户未认证");
        }
        User user = JwtUtil.getUserNameByToken(token);
        collection.setUserId(user.getId());
        Boolean result = collectionService.userSavePost(collection);
        return Result.success(result);
    }

}
