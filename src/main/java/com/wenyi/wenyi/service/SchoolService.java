package com.wenyi.wenyi.service;

import com.wenyi.wenyi.entity.School;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 22895
* @description 针对表【school】的数据库操作Service
* @createDate 2024-04-24 22:24:49
*/
public interface SchoolService extends IService<School> {
    // 获取全国大学数据
    List<School> getAllScroll();

    // 根据输入学校模糊搜索
    List<School> searchSchool(String schoolName);

    // 根据学校id查询学校
    School getSchoolById(String schoolId);
}
