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
 * @TableName topics
 */
@TableName(value ="topics")
@Data
public class Topics implements Serializable {
    /**
     * 话题id
     */
    @TableId(type = IdType.AUTO)
    private Integer topicId;

    /**
     * 话题标题
     */
    private String title;

    /**
     * 话题描述
     */
    private String description;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 封面
     */
    private String coverImg;

    /**
     * 搜索数量
     */
    private Integer watchNum;

    /**
     * 参与数量
     */
    private Integer joinCount;

    /**
     * 热度
     */
    private String heat;

    /**
     * 搜索量
     */
    private Integer searchNumber;

    /**
     * 0：关闭；1：开启；2：已过期
     */
    private String state;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}