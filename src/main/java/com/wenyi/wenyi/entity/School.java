package com.wenyi.wenyi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName school
 */
@TableName(value ="school")
@Data
public class School implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer id;

    /**
     * 
     */
    private String schoolId;

    /**
     * 
     */
    private String schoolName;

    /**
     * 
     */
    private String provinceId;

    /**
     * 
     */
    private String provinceName;

    /**
     * 
     */
    private String cityId;

    /**
     * 
     */
    private String cityName;

    /**
     * 
     */
    private String level;

    /**
     * 
     */
    private String department;

    /**
     * 
     */
    private String other;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}