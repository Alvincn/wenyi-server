package com.wenyi.wenyi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenyi.wenyi.entity.HeritageCollection;
import com.wenyi.wenyi.service.HeritageCollectionService;
import com.wenyi.wenyi.mapper.HeritageCollectionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 22895
 * @description 针对表【heritage_collection】的数据库操作Service实现
 * @createDate 2024-04-26 23:35:33
 */
@Service
public class HeritageCollectionServiceImpl extends ServiceImpl<HeritageCollectionMapper, HeritageCollection>
        implements HeritageCollectionService{

    @Override
    public Boolean addHeritageCollection(HeritageCollection heritageCollection) {
        return this.save(heritageCollection);
    }

    @Override
    public Boolean removeHeritageCollection(HeritageCollection heritageCollection) {
        return this.remove(new QueryWrapper<HeritageCollection>().eq("user_id", heritageCollection.getUserId()).eq("heritage_id", heritageCollection.getHeritageId()));
    }

    @Override
    public List<HeritageCollection> getAllHeritageCollection(Integer userId) {
        return this.list(new QueryWrapper<HeritageCollection>().eq("user_id", userId));
    }

    @Override
    public Boolean userCollectionHeritage(HeritageCollection heritageCollection) {
        HeritageCollection heritageCollection1 =  this.getOne(new QueryWrapper<HeritageCollection>().eq("user_id", heritageCollection.getUserId()).eq("heritage_id", heritageCollection.getHeritageId()));
        return heritageCollection1!=null;
    }
}




