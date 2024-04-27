package com.wenyi.wenyi.controller;

import com.wenyi.wenyi.entity.HeritageCollection;
import com.wenyi.wenyi.entity.Result;
import com.wenyi.wenyi.entity.User;
import com.wenyi.wenyi.service.HeritageCollectionService;
import com.wenyi.wenyi.service.impl.HeritageCollectionServiceImpl;
import com.wenyi.wenyi.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/heritage")
public class HeritageCollectionController {

    @Autowired
    private HeritageCollectionService heritageCollectionService;

    @PostMapping("/addHeritageCollection")
    public Result addHeritageCollection(@RequestBody HeritageCollection heritageCollection, @RequestHeader(value = "Authorization", required = false) String token) {
        if(token == null || token.isEmpty()){
            return Result.fail(402, "用户未认证");
        }
        User user = JwtUtil.getUserNameByToken(token);
        heritageCollection.setUserId(user.getId());
        Boolean flag = heritageCollectionService.addHeritageCollection(heritageCollection);
        return flag? Result.success(): Result.fail(205, "操作失败");
    }

    @PostMapping("/removeHeritageCollection")
    public Result removeHeritageCollection(@RequestBody HeritageCollection heritageCollection, @RequestHeader(value = "Authorization", required = false) String token) {
        if(token == null || token.isEmpty()){
            return Result.fail(402, "用户未认证");
        }
        User user = JwtUtil.getUserNameByToken(token);
        heritageCollection.setUserId(user.getId());
        Boolean flag = heritageCollectionService.removeHeritageCollection(heritageCollection);
        return flag? Result.success(): Result.fail(205, "操作失败");
    }

    @GetMapping("/getMyCollectionHeritage")
    public Result getMyHeritageCollection(Integer userId) {
        List<HeritageCollection> heritageCollectionList = heritageCollectionService.getAllHeritageCollection(userId);
        return Result.success(heritageCollectionList);
    }

    @GetMapping("/isCollectionHeritage")
    public Result isCollectionHeritage(HeritageCollection heritageCollection, @RequestHeader(value = "Authorization", required = false) String token) {
        if(token == null || token.isEmpty()){
            return Result.fail(402, "用户未认证");
        }
        User user = JwtUtil.getUserNameByToken(token);
        heritageCollection.setUserId(user.getId());
        Boolean flag = heritageCollectionService.userCollectionHeritage(heritageCollection);
        return  Result.success(flag);
    }
}
