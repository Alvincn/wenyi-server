package com.wenyi.wenyi.controller;

import com.wenyi.wenyi.entity.Result;
import com.wenyi.wenyi.entity.Topics;
import com.wenyi.wenyi.service.TopicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    private TopicsService topicsService;

    @GetMapping("/getOpenTopic")
    public Result getOpenTopic() {
        List<Topics> topicsList = this.topicsService.getAllOpenTopics();
        return Result.success(topicsList, "查询成功");
    }
}
