package com.wenyi.wenyi.controller;

import com.wenyi.wenyi.entity.HeritageList;
import com.wenyi.wenyi.entity.Result;
import com.wenyi.wenyi.service.HeritageListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/heritageList")
public class HeritageListController {

    @Autowired
    private HeritageListService heritageListService;

    @GetMapping("/localList")
    public Result localList(String location) {
        List<HeritageList> heritageListList = heritageListService.getListByLocation(location);
        return Result.success(heritageListList);
    }
}
