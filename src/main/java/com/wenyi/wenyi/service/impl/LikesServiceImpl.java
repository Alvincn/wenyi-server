package com.wenyi.wenyi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenyi.wenyi.entity.Collection;
import com.wenyi.wenyi.entity.Likes;
import com.wenyi.wenyi.entity.Posts;
import com.wenyi.wenyi.entity.User;
import com.wenyi.wenyi.service.LikesService;
import com.wenyi.wenyi.mapper.LikesMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
* @author 22895
* @description 针对表【likes】的数据库操作Service实现
* @createDate 2024-04-25 12:56:19
*/
@Service
public class LikesServiceImpl extends ServiceImpl<LikesMapper, Likes>
    implements LikesService{

    private final PostsServiceImpl postsServiceImpl;
    private final UserServiceImpl userServiceImpl;

    public LikesServiceImpl(PostsServiceImpl postsServiceImpl, UserServiceImpl userServiceImpl) {
        this.postsServiceImpl = postsServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public Boolean addLikes(Likes likes) {
        // 是否保存成功
        Boolean saveSuccess = this.save(likes);
        // post中数据加一
        Posts posts = postsServiceImpl.getById(likes.getPostId());
        posts.setLikeNumber(posts.getLikeNumber() + 1);
        Boolean updateSuccess = postsServiceImpl.updateById(posts);
        return saveSuccess && updateSuccess;
    }

    @Override
    public Boolean deleteLikes(Likes likes) {
        // 是否删除成功
        Boolean deleteSuccess = this.remove(new QueryWrapper<Likes>().eq("user_id", likes.getUserId()).eq("post_id", likes.getPostId()));
        // post中数据减一
        Posts posts = postsServiceImpl.getById(likes.getPostId());
        posts.setLikeNumber(posts.getLikeNumber() - 1);
        Boolean updateSuccess = postsServiceImpl.updateById(posts);
        return deleteSuccess && updateSuccess;
    }

    @Override
    public List<Likes> getLikes(Integer user_id) {
        List<Likes> likesList = this.list(new QueryWrapper<Likes>().eq("user_id", user_id));
        List<Likes> newLikes = new ArrayList<>();
        likesList.forEach(v -> {
            Posts posts = postsServiceImpl.getById(v.getPostId());
            if(Objects.equals(posts.getPostStatus(), "2") && posts.getPrivateStatus() == 1){
                User user = userServiceImpl.getById(v.getUserId());
                v.setPosts(posts);
                v.setUser(userServiceImpl.clearUser(user));
                newLikes.add(v);
            }
        });
        return newLikes;
    }

    @Override
    public Boolean userLikesPost(Likes likes) {
        Likes likes1 = this.getOne(new QueryWrapper<Likes>().eq("user_id", likes.getUserId()).eq("post_id", likes.getPostId()));
        return likes1 != null;
    }

}




