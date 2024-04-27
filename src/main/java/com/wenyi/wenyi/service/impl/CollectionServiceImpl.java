package com.wenyi.wenyi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenyi.wenyi.entity.Collection;
import com.wenyi.wenyi.entity.Posts;
import com.wenyi.wenyi.entity.User;
import com.wenyi.wenyi.service.CollectionService;
import com.wenyi.wenyi.mapper.CollectionMapper;
import com.wenyi.wenyi.service.PostsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
* @author 22895
* @description 针对表【collection】的数据库操作Service实现
* @createDate 2024-04-25 02:54:44
*/
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection>
    implements CollectionService{

    private final UserServiceImpl userServiceImpl;
    private final PostsServiceImpl postsServiceImpl;

    public CollectionServiceImpl(UserServiceImpl userServiceImpl, PostsServiceImpl postsServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.postsServiceImpl = postsServiceImpl;
    }

    @Override
    public Boolean addCollection(Collection collection) {
        // 是否保存成功
        Boolean saveSuccess = this.save(collection);
        // post中数据加一
        Posts posts = postsServiceImpl.getById(collection.getPostId());
        posts.setCollectionNumber(posts.getCollectionNumber() + 1);
        Boolean updateSuccess = postsServiceImpl.updateById(posts);
        return saveSuccess && updateSuccess;
    }

    @Override
    public Boolean deleteCollection(Collection collection) {
        // 是否删除成功
        Boolean deleteSuccess = this.remove(new QueryWrapper<Collection>().eq("user_id", collection.getUserId()).eq("post_id", collection.getPostId()));
        // post中数据减一
        Posts posts = postsServiceImpl.getById(collection.getPostId());
        posts.setCollectionNumber(posts.getCollectionNumber() - 1);
        Boolean updateSuccess = postsServiceImpl.updateById(posts);
        return deleteSuccess && updateSuccess;
    }

    @Override
    public List<Collection> getCollections(Integer user_id) {
        List<Collection> collections = this.list(new QueryWrapper<Collection>().eq("user_id", user_id));
        List<Collection> newCollections = new ArrayList<>();
        collections.forEach(v -> {
            Posts posts = postsServiceImpl.getById(v.getPostId());
            if(Objects.equals(posts.getPostStatus(), "2") && posts.getPrivateStatus() == 1){
                User user = userServiceImpl.getById(v.getUserId());
                v.setPosts(posts);
                v.setUser(userServiceImpl.clearUser(user));
                newCollections.add(v);
            }
        });
        return newCollections;
    }

    @Override
    public Boolean userSavePost(Collection collection) {
        Collection collection1 = this.getOne(new QueryWrapper<Collection>().eq("user_id", collection.getUserId()).eq("post_id", collection.getPostId()));
        return collection1 != null;
    }
}




