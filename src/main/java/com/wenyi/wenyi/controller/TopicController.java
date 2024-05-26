package com.wenyi.wenyi.controller;

import com.wenyi.wenyi.entity.Posts;
import com.wenyi.wenyi.entity.Result;
import com.wenyi.wenyi.entity.Topics;
import com.wenyi.wenyi.service.TopicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    private TopicsService topicsService;

    // 获取所有开启的话题
    @GetMapping("/getOpenTopic")
    public Result getOpenTopic() {
        List<Topics> topicsList = this.topicsService.getAllOpenTopics();
        return Result.success(topicsList, "查询成功");
    }

    // 排序获取话题
    @GetMapping("/getHeatTopic")
    public Result getHeatTopic() {
        List<Topics> topicsList = this.topicsService.getAllTopics();
        return Result.success(topicsList);
    }

    // 根据id获取到该话题详情
    @GetMapping("/getTopicDetail")
    public Result getTopicDetail(Integer id) {
        Topics topics = this.topicsService.getTopicById(id);
        return Result.success(topics);
    }

    // 根据话题id获取该话题下所有的帖子
    @GetMapping("/getPostsByTopicId")
    public Result getAllTopicsByTopicId(Integer topicId) {
        List<Posts> posts = this.topicsService.getAllPostsByTopicId(topicId);
        return Result.success(posts);
    }

    // 直接获取全部话题
    @GetMapping("/getTopics")
    public Result getTopics() {
        List<Topics> topicsList = this.topicsService.getTopics();
        return Result.success(topicsList);
    }

    // 保存话题
    @PostMapping("/saveTopics")
    public Result saveTopics(@RequestBody Topics topics) {
        Boolean saveFlag = this.topicsService.saveTopics(topics);
        return saveFlag? Result.success(): Result.fail();
    }

    // 删除
    @PostMapping("/deleteTopics")
    public Result deleteTopicsById(@RequestBody Topics topics) {
        Boolean deleteFlag = this.topicsService.deleteTopicsById(topics);
        return deleteFlag? Result.success(): Result.fail();
    }
}
