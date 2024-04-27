package com.wenyi.wenyi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenyi.wenyi.entity.Province;
import com.wenyi.wenyi.service.ProvinceService;
import com.wenyi.wenyi.mapper.ProvinceMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 22895
* @description 针对表【province】的数据库操作Service实现
* @createDate 2024-04-24 18:18:53
*/
@Service
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, Province>
    implements ProvinceService{

    @Override
    public List<Province> getAllProvince() {
        return this.list(new QueryWrapper<Province>().eq("city", "0"));
    }

    @Override
    public List<Province> getAllCitiesByProvinceId(Integer pId) {
        Province province = this.getById(pId);
        return this.list(new QueryWrapper<Province>()
                .eq("province", province.getProvince())
                .and(q->q.eq("area", province.getArea())
                        .ne("city", province.getCity())));
    }

    @Override
    public List<Province> getAllAreaByCityId(Integer pId) {
        Province province = this.getById(pId);
        return this.list(
                new QueryWrapper<Province>()
                        .eq("province", province.getProvince())
                        .eq("city", province.getCity())
                        .eq("town", province.getTown())
        );
    }

    @Override
    public List<Province> getAllTownByAreaId(Integer pId) {
        Province province = this.getById(pId);
        return this.list(
                new QueryWrapper<Province>()
                    .eq("province", province.getProvince())
                    .eq("city", province.getCity())
                    .eq("area", province.getArea())
                    .ne("town", "0")

        );
    }
}




