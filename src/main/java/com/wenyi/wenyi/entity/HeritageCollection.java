package com.wenyi.wenyi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName heritage_collection
 */
@TableName(value ="heritage_collection")
@Data
public class HeritageCollection implements Serializable {
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
     * 非遗主键
     */
    private Integer heritageId;

    /**
     * 非遗标题
     */
    private String heritageName;

    /**
     * 申办单位
     */
    private String heritageLocation;

    /**
     * 内容
     */
    private String heritageContent;

    /**
     * 内容
     */
    private String rxTime;

    /**
     * 整条信息
     */
    private String totalInfo;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}