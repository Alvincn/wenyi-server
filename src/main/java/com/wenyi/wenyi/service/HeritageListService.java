package com.wenyi.wenyi.service;

import com.wenyi.wenyi.entity.HeritageList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 22895
* @description 针对表【heritage_list】的数据库操作Service
* @createDate 2024-04-29 01:32:52
*/
public interface HeritageListService extends IService<HeritageList> {
    // 根据地点搜索
    List<HeritageList> getListByLocation(String location);
}
