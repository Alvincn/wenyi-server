package com.wenyi.wenyi.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenyi.wenyi.entity.School;
import com.wenyi.wenyi.entity.User;
import com.wenyi.wenyi.service.UserService;
import com.wenyi.wenyi.mapper.UserMapper;
import com.wenyi.wenyi.utils.JwtUtil;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
* @author 22895
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-04-18 14:21:46
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    private final SchoolServiceImpl schoolService;

    public UserServiceImpl(SchoolServiceImpl schoolServiceImpl) {
        this.schoolService = schoolServiceImpl;
    }

    @Override
    public User findByUsername(String username) {
        return this.getOne(new QueryWrapper<User>().eq("username", username));
    }

    @Override

    public Boolean register(User user) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        user.setRegisterTime(date);
        user.setNickname("文官" + this.list().size());
        // md5加密
        String md5Password = DigestUtil.md5Hex(user.getPassword());
        // 设置进去
        user.setPassword(md5Password);
        return this.save(user);
    }

    @Override
    public String login(User user) {
        String md5Password = DigestUtil.md5Hex(user.getPassword());
        // 解密
        User loginUser = this.findByUsername(user.getUsername());
        // 查看密码是否相同
        boolean result = Objects.equals(md5Password, loginUser.getPassword());
        // 密码相同，派发Token
        if(result) {
            return JwtUtil.sign(loginUser);
        }else {
            return "";
        }
    }

    @Override
    public User findByUserId(Integer userId) {
        User user = this.getById(userId);
        if(user.getSchoolId() != null) {
            user.setSchoolName(schoolService.getSchoolById(user.getSchoolId()).getSchoolName());
        }
        return user;
    }


    @Override
    public Boolean updateUserInfo(User user) {

        return this.updateById(user);
    }

    @Override
    public List<User> findByKeyword(String keyword) {
        // 获取学校列表
        List<School> schoolList = schoolService.searchSchool(keyword);
        List<User> userList = new ArrayList<>();
        // 学校没搜索到，去搜索用户昵称，地址
        if(schoolList.isEmpty()){
             userList = this.list(new QueryWrapper<User>().like("nickname", keyword).or().like("location", keyword));
        }else {
            // 搜索到学校了，需要对学校取出id，去用户里搜索schoolid
            for (School school : schoolList) {
                // 把所有关于该学校的用户筛选出来
                List<User> userList1 = this.list(new QueryWrapper<User>().eq("school_id", school.getSchoolId()));
                if(!userList1.isEmpty()){
                    userList1 = userList1.stream().peek(user -> user.setSchoolName(school.getSchoolName())).toList();
                    // 这些是筛选出来的学校相关的
                    userList.addAll(userList1);
                }
            }
            // 筛选出来昵称和地址相关的
            List<User> userList2 = this.list(new QueryWrapper<User>().like("nickname", keyword).or().like("location", keyword));
            userList.addAll(userList2);
        }
        userList = userList.stream().map(this::clearUser).toList();
        Map<Integer, User> uniqueUsersById = userList.stream()
        .collect(Collectors.toMap(
                User::getId, // 键映射函数，使用用户的ID作为键
                Function.identity(), // 值映射函数，直接返回用户对象
                (existing, replacement) -> existing, // 合并函数，当键冲突时保留现有的值（即第一个遇到的用户）
                LinkedHashMap::new // 使用LinkedHashMap来保持插入顺序（如果需要的话）
        ));
        return new ArrayList<>(uniqueUsersById.values());
    }

    @Override
    public List<User> findAll() {
        List<User> list = this.list();
        list.forEach(user -> {
            user.setPassword(null);
        });
        return list;
    }

    /**
     * 清除一些不必要的属性
     */
    public User clearUser(User user) {
        user.setEmail(null);
        user.setPassword(null);
        user.setRegisterTime(null);
        user.setSchoolId(null);
        user.setLocationArr(null);
        user.setUsername(null);
        user.setTag(null);
        return user;
    }

}




