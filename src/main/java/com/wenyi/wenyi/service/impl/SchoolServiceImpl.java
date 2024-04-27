package com.wenyi.wenyi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenyi.wenyi.entity.School;
import com.wenyi.wenyi.service.SchoolService;
import com.wenyi.wenyi.mapper.SchoolMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 22895
* @description 针对表【school】的数据库操作Service实现
* @createDate 2024-04-24 22:24:49
*/
@Service
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School>
    implements SchoolService{

    @Override
    public List<School> getAllScroll() {
        return this.list();
    }

    @Override
    public List<School> searchSchool(String schoolName) {
        return this.list(new QueryWrapper<School>().like("school_name", schoolName));
    }

    @Override
    public School getSchoolById(String schoolId) {
        return this.getOne(new QueryWrapper<School>().eq("school_id", schoolId));
    }


}




