package com.wenyi.wenyi.controller;

import com.wenyi.wenyi.entity.Province;
import com.wenyi.wenyi.entity.Result;
import com.wenyi.wenyi.service.ProvinceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/area")
public class ProvinceController {

    private static final Logger log = LoggerFactory.getLogger(ProvinceController.class);
    @Autowired
    private ProvinceService provinceService;

    /**
     * 获取全部省市
     * @return
     */
    @GetMapping("/getProvince")
    public Result getProvince() {
        List<Province> proviceList = provinceService.getAllProvince();
        return Result.success(proviceList, "查询成功");
    }
    /**
     * 根据省id获取该省下全部市
     */
    @GetMapping("/getCities")
    public Result getCities(Integer pId) {
        List<Province> cities = provinceService.getAllCitiesByProvinceId(pId);
        return Result.success(cities, "查询成功");
    }

    /**
     * 根据市id，获取全部的县级
     */
    @GetMapping("/getAreas")
    public Result getAreas(Integer pId) {
        List<Province> areas = provinceService.getAllAreaByCityId(pId);
        return Result.success(areas, "查询成功");
    }

    /**
     * 根据省市id，获取到所有的乡级
     */
    @GetMapping("/getTowns")
    public Result getTowns(Integer pId) {
        pId = pId - 1;
        List<Province> areas = provinceService.getAllTownByAreaId(pId);
        return Result.success(areas, "查询成功");
    }

}
