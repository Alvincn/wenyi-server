package com.wenyi.wenyi.service;

import com.wenyi.wenyi.entity.Topics;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 22895
* @description 针对表【topics】的数据库操作Service
* @createDate 2024-04-22 01:51:34
*/
public interface TopicsService extends IService<Topics> {
    List<Topics> getAllOpenTopics();
}
