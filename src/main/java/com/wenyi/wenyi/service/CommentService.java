package com.wenyi.wenyi.service;

import com.wenyi.wenyi.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 22895
* @description 针对表【comment】的数据库操作Service
* @createDate 2024-04-25 22:52:07
*/
public interface CommentService extends IService<Comment> {

    // 新增评论
    Boolean addComment(Comment comment);

    // 删除评论
    Boolean deleteComment(Integer id);

    // 评论评论
    Boolean updateComment(Comment comment);

    /**
     * 喜欢评论
     * @param id 评论id
    */

    Boolean likeComment(Integer id);

    /**
     * 不喜欢此评论
     * @param id 评论id
     * @return
     */
    Boolean unlikeComment(Integer id);

    /**
     * 获取帖子下全部评论
     * @param postId 帖子id
    */
    Comment getCommentByPostId(Integer postId);


}
