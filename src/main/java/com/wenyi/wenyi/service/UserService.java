package com.wenyi.wenyi.service;

import com.wenyi.wenyi.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 22895
* @description 针对表【user】的数据库操作Service
* @createDate 2024-04-18 17:37:53
*/
public interface UserService extends IService<User> {

    User findByUsername(String username);

    Boolean register(User user);

    String login(User user);

    // 根据用户id查找用户信息
    User findByUserId(Integer userId);

    // 更新用户信息
    Boolean updateUserInfo(User user);
}
