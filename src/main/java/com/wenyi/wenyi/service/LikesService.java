package com.wenyi.wenyi.service;

import com.wenyi.wenyi.entity.Collection;
import com.wenyi.wenyi.entity.Likes;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 22895
* @description 针对表【likes】的数据库操作Service
* @createDate 2024-04-25 12:56:19
*/
public interface LikesService extends IService<Likes> {
    // 添加喜欢
    Boolean addLikes(Likes likes);

    // 取消喜欢
    Boolean deleteLikes(Likes likes);

    // 获取某人所有的喜欢
    List<Likes> getLikes(Integer user_id);

    // 某人是否喜欢了这条帖子
    Boolean userLikesPost(Likes likes);
}
