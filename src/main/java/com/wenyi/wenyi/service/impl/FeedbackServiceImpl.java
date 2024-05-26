package com.wenyi.wenyi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenyi.wenyi.entity.Feedback;
import com.wenyi.wenyi.service.FeedbackService;
import com.wenyi.wenyi.mapper.FeedbackMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author 22895
* @description 针对表【feedback】的数据库操作Service实现
* @createDate 2024-04-30 16:27:01
*/
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback>
    implements FeedbackService{

    @Override
    public Boolean saveFeedback(Feedback feedback) {
        return this.save(feedback);
    }

    @Override
    public Feedback getFeedbackById(Integer id) {
        return this.getOne(new QueryWrapper<Feedback>().eq("id", id));
    }

    @Override
    public List<Feedback> getMyFeedBack(Integer userId) {
        return this.list(new QueryWrapper<Feedback>().eq("user_id", userId));
    }

    @Override
    public List<Feedback> getAllFeedBack() {
        return this.list(new QueryWrapper<Feedback>().eq("status", "0").or().eq("status", "1"));
    }

    @Override
    public List<Feedback> getAllReplyFeedback() {
        return this.list(new QueryWrapper<Feedback>().eq("status", "2"));
    }

    @Override
    public List<Feedback> getAllNotReplyFeedback() {
        return this.list(new QueryWrapper<Feedback>().eq("status", "0"));
    }

    @Override
    public Boolean replyFeedback(Feedback feedback) {
        feedback.setStatus("2");
        feedback.setReturnTime(new Date());
        return this.updateById(feedback);
    }

    @Override
    public List<Feedback> getAllFeedbackWithoutStatus() {
        return this.list();
    }

}




