package com.wenyi.wenyi.service;

import com.wenyi.wenyi.entity.Posts;
import com.wenyi.wenyi.entity.Topics;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 22895
* @description 针对表【topics】的数据库操作Service
* @createDate 2024-04-22 01:51:34
*/
public interface TopicsService extends IService<Topics> {

    // 获取所有开启的话题
    List<Topics> getAllOpenTopics();

    // 获取所有话题，使用热度排序
    List<Topics> getAllTopics();

    // 根据话题id获取该话题
    Topics getTopicById(Integer id);

    // 获取一个话题下所有帖子
    List<Posts> getAllPostsByTopicId(Integer topicId);

    // 添加话题
    Boolean saveTopics(Topics topics);

    // 获取全部帖子，无热度
    List<Topics> getTopics();

    // 删除话题
    Boolean deleteTopicsById(Topics topics);
}
