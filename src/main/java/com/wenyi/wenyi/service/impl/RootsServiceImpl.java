package com.wenyi.wenyi.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenyi.wenyi.entity.Roots;
import com.wenyi.wenyi.entity.User;
import com.wenyi.wenyi.service.RootsService;
import com.wenyi.wenyi.mapper.RootsMapper;
import com.wenyi.wenyi.utils.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
* @author 22895
* @description 针对表【roots】的数据库操作Service实现
* @createDate 2024-05-18 16:45:29
*/
@Service
public class RootsServiceImpl extends ServiceImpl<RootsMapper, Roots>
    implements RootsService{
    @Override
    public String login(Roots roots) {
        String md5Password = DigestUtil.md5Hex(roots.getPassword());
        // 解密
        Roots loginRoot = this.findByUsername(roots.getUsername());
        // 查看密码是否相同
        boolean result = Objects.equals(md5Password, loginRoot.getPassword());
        // 密码相同，派发Token
        if(result) {
            return JwtUtil.sign(loginRoot);
        }else {
            return null;
        }
    }

    public Roots findByUsername(String username) {
        return this.getOne(new QueryWrapper<Roots>().eq("username", username));
    }
}




