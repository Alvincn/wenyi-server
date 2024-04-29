package com.wenyi.wenyi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName comment
 */
@TableName(value ="comment")
@Data
public class Comment implements Serializable {
    @TableField(exist = false)
    private User user;
    /**
     * 评论id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 评论用户id
     */
    private Integer userId;

    /**
     * 帖子id
     */
    private Integer postId;

    /**
     * 评论内容
     */
    private String context;

    /**
     * 点赞数量
     */
    private Integer likeNumber;

    /**
     * 不喜欢数量
     */
    private Integer dislikeNumber;

    /**
     * 评论的评论的内容
     */
    private String contextJson;

    /**
     * 评论时间
     */
    private Date commentTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}