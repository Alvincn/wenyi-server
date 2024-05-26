package com.wenyi.wenyi.controller;

import com.wenyi.wenyi.entity.Result;
import com.wenyi.wenyi.entity.User;
import com.wenyi.wenyi.service.UserService;
import com.wenyi.wenyi.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
//    private UserServiceImpl userService;
    /**
     * 查找用户名是否注册过
     * @param userName 用户名
     * @return Result
     */
    @GetMapping("/findUserByUserName")
    public Result findUserById(String userName) {
        User user = userService.findByUsername(userName);
        // 用户信息不为空，说明是老用户
        return user != null? Result.success(null, "用户查询成功"): Result.fail(201, "用户未注册！");
    }

    /**
     * 注册
     * @param user 用户信息
     * @return Result
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        boolean result = userService.register(user);
        if(result) {
            return Result.success(null, "注册成功");
        }
        return Result.fail(202, "注册失败");
    }

    /**
     * 登录
     * @param user 用户信息
     * @return Result
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        User findUser = userService.findByUsername(user.getUsername());
        if(findUser == null) {
            return Result.fail(201, "用户名错误");
        }
        String result = userService.login(user);
        // 如果说为空，说明密码错误，无法派发Token
        if(result.isEmpty()) {
            return Result.fail(203, "密码错误");
        }
        return Result.success(result, "登录成功");
    }

    /**
     * 返回用户信息
     */
    @GetMapping("/getUserInfo")
    public Result getUserInfo(@RequestHeader(value = "Authorization", required = false) String token) {
        if(token == null || token.isEmpty()){
            return Result.fail(402, "用户未认证");
        }
        User user = JwtUtil.getUserNameByToken(token);
        User getUser = userService.findByUserId(user.getId());

        if(getUser == null) {
            return Result.fail(403, "认证失败");
        }
        getUser.setPassword(null);
        return Result.success(getUser, "查找成功");
    }

    /**
     * 返回用户信息
     */
    @GetMapping("/getPersonInfo")
    public Result getPersonInfo(Integer userId) {
        User getUser = userService.findByUserId(userId);
        if(getUser == null) {
            return Result.fail(403, "认证失败");
        }
        getUser.setPassword(null);
        return Result.success(getUser, "查找成功");
    }

    /**
     * 更新用户信息
     */
    @PostMapping("/updateInfo")
    public Result updateInfo(@RequestBody User user) {
        Boolean result = userService.updateUserInfo(user);
        return result? Result.success(): Result.fail(204, "更新失败");
    }

    /**z
     * 关键词搜索用户
     */
    @GetMapping("/searchUser")
    public Result searchUser(String keyword) {
        List<User> userList = userService.findByKeyword(keyword);
        return Result.success(userList);
    }

}
