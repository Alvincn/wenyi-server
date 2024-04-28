package com.wenyi.wenyi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName heritage_list
 */
@TableName(value ="heritage_list")
@Data
public class HeritageList implements Serializable {
    /**
     * 
     */
    private Integer projId;

    /**
     * 
     */
    private String projNum;

    /**
     * 
     */
    private String nameCn;

    /**
     * 
     */
    private String nameEn;

    /**
     * 
     */
    private String categorycn;

    /**
     * 
     */
    private String categoryen;

    /**
     * 
     */
    private Integer time;

    /**
     * 
     */
    private String typeCn;

    /**
     * 
     */
    private String typeEn;

    /**
     * 
     */
    private String placeCn;

    /**
     * 
     */
    private String placeEn;

    /**
     * 
     */
    private String unitCn;

    /**
     * 
     */
    private String unitEn;

    /**
     * 
     */
    private Double x;

    /**
     * 
     */
    private Double y;

    /**
     * 
     */
    private String provincecn;

    /**
     * 
     */
    private String provinceen;

    /**
     * 
     */
    private String region4cn;

    /**
     * 
     */
    private String region4en;

    /**
     * 
     */
    private String region7cn;

    /**
     * 
     */
    private String region7en;

    /**
     * 
     */
    private String center;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}