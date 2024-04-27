package com.wenyi.wenyi.controller;

import com.wenyi.wenyi.entity.Result;
import com.wenyi.wenyi.entity.School;
import com.wenyi.wenyi.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @GetMapping("/searchSchool")
    public Result searchSchool(String schoolName) {
        List<School> schoolList = this.schoolService.searchSchool(schoolName);
        return Result.success(schoolList);
    }
}
