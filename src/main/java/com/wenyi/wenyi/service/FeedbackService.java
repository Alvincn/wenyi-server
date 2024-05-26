package com.wenyi.wenyi.service;

import com.wenyi.wenyi.entity.Feedback;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 22895
* @description 针对表【feedback】的数据库操作Service
* @createDate 2024-04-30 16:27:01
*/
public interface FeedbackService extends IService<Feedback> {
    // 提交反馈
    Boolean saveFeedback(Feedback feedback);

    // 获取反馈结果
    Feedback getFeedbackById(Integer id);

    // 获取我所有的反馈
    List<Feedback> getMyFeedBack(Integer userId);

    // 获取所有的反馈
    List<Feedback> getAllFeedBack();

    // 获取所有已回复的反馈
    List<Feedback> getAllReplyFeedback();

    // 获取所有没有回复的反馈
    List<Feedback> getAllNotReplyFeedback();

    // 回复反馈
    Boolean replyFeedback(Feedback feedback);

    // 获取全部反馈
    List<Feedback> getAllFeedbackWithoutStatus();
}

