package com.wenyi.wenyi.service;

import com.wenyi.wenyi.entity.Roots;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 22895
* @description 针对表【roots】的数据库操作Service
* @createDate 2024-05-18 16:45:29
*/
public interface RootsService extends IService<Roots> {
    // 登录
    String login(Roots roots);
    // 搜索用户名
    Roots findByUsername(String username);
}
