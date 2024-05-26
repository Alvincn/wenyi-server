package com.wenyi.wenyi.controller;

import com.wenyi.wenyi.entity.Result;
import com.wenyi.wenyi.entity.Roots;
import com.wenyi.wenyi.entity.User;
import com.wenyi.wenyi.service.RootsService;
import com.wenyi.wenyi.service.UserService;
import com.wenyi.wenyi.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/root")
public class RootsController {
    
    @Autowired
    private RootsService rootsService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody Roots roots) {
        Roots roots1 = rootsService.findByUsername(roots.getUsername());
        if (roots1 != null) {
            String token = rootsService.login(roots);
            if(token != null) {
                return Result.success(token);
            }else {
                return Result.fail(203, "密码错误");
            }
        }else {
            return Result.fail(201, "用户不存在");
        }
    }

    // 获取登录用户信息
    @GetMapping("getUserInfo")
    public Result getUserInfo(@RequestHeader(value = "Authorization", required = false) String token) {
        if(token == null || token.isEmpty()){
            return Result.fail(402, "用户未认证");
        }
        Roots roots = JwtUtil.getRootsUserNameByToken(token);
        Roots roots1 = rootsService.findByUsername(roots.getUsername());
        if(roots1 != null) {
            roots1.setPassword(null);
            return Result.success(roots1);
        }else {
            return Result.fail(201, "用户不存在");
        }
    }

    /**
     * 获取所有用户信息
     */
    @GetMapping("/getAllUser")
    public Result getAllUser() {
        List<User> userList = userService.findAll();
        return Result.success(userList);
    }
}
