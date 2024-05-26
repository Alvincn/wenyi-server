package com.wenyi.wenyi.controller;

import com.wenyi.wenyi.entity.Feedback;
import com.wenyi.wenyi.entity.Result;
import com.wenyi.wenyi.entity.User;
import com.wenyi.wenyi.service.FeedbackService;
import com.wenyi.wenyi.service.UserService;
import com.wenyi.wenyi.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserService userService;

    /**
     * 根据反馈id获取该反馈
     * @param id
     * @return
     */
    @GetMapping("/getFeedback")
    public Result feedback(Integer id) {
        Feedback feedback = feedbackService.getFeedbackById(id);
        return Result.success(feedback);
    }

    /**
     * 提交反馈
     * @param feedback
     * @return
     */
    @PostMapping("/saveFeedback")
    public Result saveFeedback(@RequestHeader(value = "Authorization", required = false) String token, @RequestBody Feedback feedback) {
        if(token == null || token.isEmpty()){
            return Result.fail(402, "用户未认证");
        }
        User user = JwtUtil.getUserNameByToken(token);
        feedback.setUserId(user.getId());
        System.out.println(feedback);
        Boolean flag = feedbackService.saveFeedback(feedback);
        return Result.success(flag);
    }

    /**
     * 获取我的反馈
     * @return
     */
    @GetMapping("/getMyFeedbackList")
    public Result getMyFeedbackList(@RequestHeader(value = "Authorization", required = false) String token) {
        if(token == null || token.isEmpty()){
            return Result.fail(402, "用户未认证");
        }
        User user = JwtUtil.getUserNameByToken(token);
        List<Feedback> feedbacks = feedbackService.getMyFeedBack(user.getId());
        return Result.success(feedbacks);
    }

    /**
     * 获取所有反馈
     */
    @GetMapping("/getAllFeedbackList")
    public Result getAllFeedbackList() {
        List<Feedback> feedbacks = feedbackService.getAllFeedBack();
        return Result.success(feedbacks);
    }

    /**
     * 获取所有反馈不分状态
     */
    @GetMapping("/getAllFeedbackListWithoutStatus")
    public Result getAllFeedbackListWithoutStatus() {
        List<Feedback> feedbacks = feedbackService.getAllFeedbackWithoutStatus();
        return Result.success(feedbacks);
    }

    /**
     * 获取所有回复/未回复的反馈
     * @param status 0：
     */
    @GetMapping("/getAllReplyFeedback")
    public Result getAllReplyFeedback(Integer status) {
        List<Feedback> feedbackList = List.of();
        if(status == 0) {
            feedbackList = feedbackService.getAllNotReplyFeedback();
        }else if(status == 1) {
            feedbackList = feedbackService.getAllReplyFeedback();
        }
        return Result.success(feedbackList);
    }

    /**
     * 回复反馈
     * @param feedback
     */
    @PostMapping("/replyFeedback")
    public Result replyFeedback(@RequestBody Feedback feedback) {
        Boolean replyFlag = feedbackService.replyFeedback(feedback);
        return replyFlag? Result.success(): Result.fail();
    }
}
