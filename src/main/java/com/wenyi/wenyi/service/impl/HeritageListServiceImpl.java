package com.wenyi.wenyi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenyi.wenyi.entity.HeritageList;
import com.wenyi.wenyi.service.HeritageListService;
import com.wenyi.wenyi.mapper.HeritageListMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 22895
* @description 针对表【heritage_list】的数据库操作Service实现
* @createDate 2024-04-29 01:32:52
*/
@Service
public class HeritageListServiceImpl extends ServiceImpl<HeritageListMapper, HeritageList>
    implements HeritageListService{

    @Override
    public List<HeritageList> getListByLocation(String location) {
        return this.list(new QueryWrapper<HeritageList>().like("Place_CN", location));
    }
}




