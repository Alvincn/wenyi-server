package com.wenyi.wenyi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenyi.wenyi.entity.Posts;
import com.wenyi.wenyi.entity.Tags;
import com.wenyi.wenyi.entity.Topics;
import com.wenyi.wenyi.entity.User;
import com.wenyi.wenyi.service.PostsService;
import com.wenyi.wenyi.mapper.PostsMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author 22895
* @description 针对表【posts】的数据库操作Service实现
* @createDate 2024-04-22 15:19:16
*/
@Service
public class PostsServiceImpl extends ServiceImpl<PostsMapper, Posts>
    implements PostsService{

    private final UserServiceImpl userServiceImpl;
    private final TagsServiceImpl tagsServiceImpl;

    public PostsServiceImpl(UserServiceImpl userServiceImpl, TagsServiceImpl tagsServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.tagsServiceImpl = tagsServiceImpl;
    }

    @Override
    public List<Posts> getAllPosts() {
        List<Posts> postsList = this.list(new QueryWrapper<Posts>().eq("post_status", '2'));
        postsList.forEach(v -> {
            v.setContent(null);
            User user = userServiceImpl.clearUser(userServiceImpl.getById(v.getSenderUserid()));
            v.setUser(user);
        });
        return postsList;
    }

    @Override
    public Boolean addPost(Posts posts) {
        return this.save(posts);
    }

    @Override
    public Posts getDraftByUserId(Integer userId) {
        return this.getOne(new QueryWrapper<Posts>().eq("sender_userid", userId).eq("post_status", '0'));
    }

    @Override
    public Boolean updatePostDraft(Posts posts) {
        return this.updateById(posts);
    }

    @Override
    public Boolean deletePostDraft(Integer userId) {
        return this.remove(new QueryWrapper<Posts>().eq("sender_userid", userId).eq("post_status", '0'));
    }

    @Override
    public List<Posts> getPostsByUserId() {
        return this.list(new QueryWrapper<Posts>().eq("post_status", 2));
    }



    @Override
    public List<Posts> getUserAllPostsByUserId(Integer userId) {
        return this.list(new QueryWrapper<Posts>().eq("sender_userid", userId));
    }

    @Override
    public Posts getPostByPostId(Integer postId) {
        // 获取到该帖子
        Posts posts = this.getById(postId);
        // 获取到用户id，查询用户
        Integer userId = posts.getSenderUserid();
        User user = userServiceImpl.getById(userId);
        Tags tags = tagsServiceImpl.getById(user.getTag());
        if(tags != null) {
            user.setTagName(tags.getLabel());
        }
        user.setPassword(null);
        user.setEmail(null);
        posts.setUser(user);
        // posts浏览量加一
        posts.setWatchNumber(posts.getWatchNumber() + 1);
        this.updateById(posts);
        return posts;
    }

    @Override
    public List<Posts> searchPosts(String keyword) {
        return this.list(new QueryWrapper<Posts>().like("title", keyword).or().like("description", keyword));
    }


    @Override
    public List<Posts> getGoodPosts() {
        List<Posts> postsList = this.list(new QueryWrapper<Posts>().eq("post_status", '2').eq("cover_img", "").eq("private_status", 1));
        postsList.forEach(v -> {
            v.setUser(userServiceImpl.getById(v.getSenderUserid()));
        });
        return postsList;
    }

    @Override
    public List<Posts> getPostsByStatus(Integer status) {
        List<Posts> postsList;
        if(status == null) {
            postsList = this.list();
        }else {
            postsList = this.list(new QueryWrapper<Posts>().eq("post_status", status));
        }
        postsList.forEach(v -> {
            v.setUser(userServiceImpl.getById(v.getSenderUserid()));
        });
        return postsList;
    }

    @Override
    public Boolean updatePostStatus(Integer postId, Integer status) {
        Posts posts = this.getById(postId);
        posts.setPostStatus(status.toString());
        return this.updateById(posts);
    }

}




