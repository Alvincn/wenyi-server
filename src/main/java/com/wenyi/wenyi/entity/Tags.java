package com.wenyi.wenyi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName tags
 */
@TableName(value ="tags")
@Data
public class Tags implements Serializable {
    /**
     * 标签id
     */
    @TableId
    private Integer id;

    /**
     * 标签描述
     */
    private String label;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}