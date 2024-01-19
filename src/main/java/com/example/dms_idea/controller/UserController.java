package com.example.dms_idea.controller;

import com.example.dms_idea.pojo.Result;
import com.example.dms_idea.pojo.User;
import com.example.dms_idea.service.UserService;
import com.example.dms_idea.untils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/login")
    public Result login(String username, String password) {
        if (username == null) return Result.error("账号不能为空");
        else if (password == null) return Result.error("密码不能为空");

        if (username.length() > 20) return Result.error("账号长度需小于20位");
        else if (password.length() > 32) return Result.error("密码长度需小于32位");

        User user = userService.getUser(username, password);
        if (user == null) return Result.error("账号不存在或密码错误");

        Map<String,Object> claims = new HashMap<>();
        claims.put("id",user.getId());
        claims.put("role",user.getRole());
        String token = JwtUtil.genToken(claims);

        //把token存储到redis中
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set(token,token,1, TimeUnit.HOURS);
        System.out.println("123");
        return Result.success(token);
    }

}
