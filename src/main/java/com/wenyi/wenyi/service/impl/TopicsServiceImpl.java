package com.wenyi.wenyi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenyi.wenyi.entity.Posts;
import com.wenyi.wenyi.entity.Topics;
import com.wenyi.wenyi.entity.User;
import com.wenyi.wenyi.service.PostsService;
import com.wenyi.wenyi.service.TopicsService;
import com.wenyi.wenyi.mapper.TopicsMapper;
import com.wenyi.wenyi.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 22895
* @description 针对表【topics】的数据库操作Service实现
* @createDate 2024-04-22 01:51:34
*/
@Service
public class TopicsServiceImpl extends ServiceImpl<TopicsMapper, Topics>
    implements TopicsService{

    private final PostsService postsService;
    private final UserServiceImpl userService;

    public TopicsServiceImpl(PostsService postsService, UserServiceImpl userService) {
        this.postsService = postsService;
        this.userService = userService;
    }

    @Override
    public List<Topics> getAllOpenTopics() {
        return this.list(new QueryWrapper<Topics>()
                .eq("state", "1")
                .orderByDesc("heat")
        );
    }

    public List<Topics> getAllTopics() {
        List<Topics> topicsList = this.baseMapper.getHeatTopics();
        topicsList.forEach(item -> {
            Topics id = this.getTopicById(item.getTopicId());
            item.setCoverImg(id.getCoverImg());
            item.setStartTime(id.getStartTime());
            item.setEndTime(id.getEndTime());
            item.setState(id.getState());
        });
        return topicsList;
    }

    public List<Topics> getTopics() {
        return this.list();
    }

    @Override
    public Boolean deleteTopicsById(Topics topics) {
        return this.removeById(topics);
    }

    @Override
    public Topics getTopicById(Integer id) {
        Topics topics = this.getById(id);
        topics.setWatchNum(topics.getWatchNum() + 1);
        this.updateById(topics);
        return topics;
    }

    @Override
    public List<Posts> getAllPostsByTopicId(Integer topicId) {
        List<Posts> postsList = this.postsService.list(new QueryWrapper<Posts>().like("topic", topicId).orderByAsc("heat"));
        postsList.forEach(v -> {
            v.setContent(null);
            User user = userService.clearUser(userService.getById(v.getSenderUserid()));
            v.setUser(user);
        });
        return postsList;
    }

    @Override
    public Boolean saveTopics(Topics topics) {
        return this.save(topics);
    }
}




