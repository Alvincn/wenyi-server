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
 * @TableName user
 */
@TableName(value = "user")
@Data
public class User implements Serializable {
    @TableField(exist = false)
    private String TagName;
    @TableField(exist = false)
    private String schoolName;
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 简介
     */
    private String saying;

    /**
     * 地点
     */
    private String locationArr;

    /**
     * 地点
     */
    private String location;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 学校
     */
    private String schoolId;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 注册时间
     */
    private Date registerTime;

    /**
     * 性别
     * 1: 男，0：女
     */
    private String gender;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户标签
     */
    private Integer tag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}