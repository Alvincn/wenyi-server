package com.wenyi.wenyi.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenyi.wenyi.entity.User;
import com.wenyi.wenyi.service.UserService;
import com.wenyi.wenyi.mapper.UserMapper;
import com.wenyi.wenyi.utils.JwtUtil;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

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
    /**
     * 清除一些不必要的属性
     */
    public User clearUser(User user) {
        user.setEmail(null);
        user.setPassword(null);
        user.setBirthday(null);
        user.setGender(null);
        user.setRegisterTime(null);
        user.setSchoolId(null);
        user.setLocation(null);
        user.setLocationArr(null);
        user.setUsername(null);
        user.setTag(null);
        return user;
    }

}




