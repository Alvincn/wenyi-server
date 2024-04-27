package com.wenyi.wenyi.service;

import com.wenyi.wenyi.entity.HeritageCollection;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 22895
 * @description 针对表【heritage_collection】的数据库操作Service
 * @createDate 2024-04-26 23:35:33
 */
public interface HeritageCollectionService extends IService<HeritageCollection> {
    // 添加收藏
    Boolean addHeritageCollection(HeritageCollection heritageCollection);
    // 取消收藏
    Boolean removeHeritageCollection(HeritageCollection heritageCollection);
    // 获取所有收藏的非遗
    List<HeritageCollection> getAllHeritageCollection(Integer userId);
    // 用户是否喜欢了此非遗
    Boolean userCollectionHeritage(HeritageCollection heritageCollection);
}
