package com.wenyi.wenyi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenyi.wenyi.entity.Topics;
import com.wenyi.wenyi.service.TopicsService;
import com.wenyi.wenyi.mapper.TopicsMapper;
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

    @Override
    public List<Topics> getAllOpenTopics() {
        return this.list(new QueryWrapper<Topics>()
                .eq("state", "1")
                .orderByDesc("heat")
        );
    }
}




