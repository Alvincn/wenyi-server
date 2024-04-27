package com.wenyi.wenyi.entity;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

/**
 * 
 * @TableName posts
 */
@TableName(value ="posts")
@Data
public class Posts implements Serializable {
    @TableField(exist = false)
    private User user;
    /**
     * 帖子Id
     */
    @TableId(type = IdType.AUTO)
    private Integer postId;

    /**
     * 标题
     */
    private String title;

    /**
     * 简单描述
     */
    private String description;

    /**
     * 具体内容
     */
    private String content;

    /**
     * 封面
     */
    private String coverImg;

    /**
     * 作者id
     */
    private Integer senderUserid;

    /**
     * 发布时间
     */
    private Timestamp senderTime;

    /**
     * 查看量
     */
    private Integer watchNumber;

    /**
     * 喜欢数量
     */
    private Integer likeNumber;

    /**
     * 收藏的数量
     */
    private Integer collectionNumber;

    /**
     * 评论数量
     */
    private Integer commentsNumber;

    /**
     * 发布地址
     */
    private String location;

    /**
     * 地区对应的数组
     */
    private String locationArr;

    /**
     * 对应的话题Id
     */
    private String topic;

    /**
     * 0：私密；1：公开
     */
    private Integer privateStatus;

    /**
     * 0：草稿；1：未审核；2：审核通过；3：审核不通过
     */
    private String postStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}