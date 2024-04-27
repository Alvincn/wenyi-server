package com.wenyi.wenyi.service;

import com.wenyi.wenyi.entity.Province;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 22895
* @description 针对表【province】的数据库操作Service
* @createDate 2024-04-24 18:18:53
*/
public interface ProvinceService extends IService<Province> {
    List<Province> getAllProvince();

    List<Province> getAllCitiesByProvinceId(Integer pId);

    List<Province> getAllAreaByCityId(Integer pId);

    List<Province> getAllTownByAreaId(Integer pId);
}
