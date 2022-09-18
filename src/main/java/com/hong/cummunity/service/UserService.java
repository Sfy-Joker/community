package com.hong.cummunity.service;

import com.hong.cummunity.mapper.UserMapper;
import com.hong.cummunity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        User dbUser = userMapper.findByAccount(user.getAccountId());
        if (dbUser == null) {
            //添加用户
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.addUser(user);
        } else {
            //更新用户
            user.setGmtModified(user.getGmtCreate());
            user.setName(user.getName());
            user.setGmtModified(System.currentTimeMillis());
            user.setAvatarUrl(user.getAvatarUrl());
            user.setToken(user.getToken());
            user.setId(dbUser.getId());
            userMapper.updateUser(user);
        }
    }
}
