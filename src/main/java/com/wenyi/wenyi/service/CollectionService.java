package com.wenyi.wenyi.service;

import com.wenyi.wenyi.entity.Collection;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 22895
* @description 针对表【collection】的数据库操作Service
* @createDate 2024-04-25 02:54:44
*/
public interface CollectionService extends IService<Collection> {
    // 添加收藏
    Boolean addCollection(Collection collection);

    // 取消收藏
    Boolean deleteCollection(Collection collection);

    // 获取某人所有的收藏
    List<Collection> getCollections(Integer user_id);

    // 某人是否收藏了这条帖子
    Boolean userSavePost(Collection collection);
}
