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
 * @TableName feedback
 */
@TableName(value ="feedback")
@Data
public class Feedback implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 类型：1：bug；2：功能优化；3：调整反馈；4：其他
     */
    private String type;

    /**
     * 提交时间
     */
    private Date submitTime;

    /**
     * 内容
     */
    private String content;

    /**
     * 上传图片列表
     */
    private String imgs;

    /**
     * 状态：0：提交；1：未回复；3：已回复
     */
    private String status;

    /**
     * 回复消息
     */
    private String returnText;

    /**
     * 回复时间
     */
    private Date returnTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}