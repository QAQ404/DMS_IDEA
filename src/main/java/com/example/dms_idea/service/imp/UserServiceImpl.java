package com.example.dms_idea.service.imp;

import com.example.dms_idea.dao.UserMapper;
import com.example.dms_idea.pojo.Result;
import com.example.dms_idea.pojo.User;
import com.example.dms_idea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(String username, String password) {
        return userMapper.getUser(username, DigestUtils.md5DigestAsHex(password.getBytes()));
    }

    @Override
    public Result addUser(String username, String password, String name, int role) {
        if (userMapper.ifUsernameHave(username) == null) {
            userMapper.addUser(username,DigestUtils.md5DigestAsHex(password.getBytes()),name,role);
            String id = userMapper.getUserIdByUsername(username);
            return Result.success(id);
        } else return Result.error("账号已存在");
    }
}
