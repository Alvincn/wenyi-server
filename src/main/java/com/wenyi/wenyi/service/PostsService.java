package com.wenyi.wenyi.service;

import com.wenyi.wenyi.entity.Posts;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 22895
* @description 针对表【posts】的数据库操作Service
* @createDate 2024-04-22 15:19:16
*/
public interface PostsService extends IService<Posts> {
    // 获取全部帖子
    List<Posts> getAllPosts();

    // 发布帖子
    Boolean addPost(Posts posts);

    // 查询用户是否有保存过的草稿
    Posts getDraftByUserId(Integer userId);

    // 更新草稿信息
    Boolean updatePostDraft(Posts posts);

    // 删除草稿
    Boolean deletePostDraft(Integer userId);

    // 获取所有的帖子
    List<Posts> getPostsByUserId();

    // 获取用户所有的帖子
    List<Posts> getUserAllPostsByUserId(Integer userId);

    // 根据postId获取该帖子
    Posts getPostByPostId(Integer postId);

    // 关键词搜索帖子
    List<Posts> searchPosts(String keyword);
}
