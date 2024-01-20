package com.example.dms_idea.controller;

import com.example.dms_idea.pojo.Result;
import com.example.dms_idea.pojo.User;
import com.example.dms_idea.service.UserService;
import com.example.dms_idea.untils.JwtUtil;
import com.example.dms_idea.untils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")  //登录
    public Result login(String username, String password) {
        if (username.length() == 0) return Result.error("账号不能为空");
        else if (password.length() == 0) return Result.error("密码不能为空");

        if (username.length() > 20) return Result.error("账号长度需小于20位");
        else if (password.length() > 32) return Result.error("密码长度需小于32位");

        User user = userService.getUser(username, password);
        if (user == null) return Result.error("账号不存在或密码错误");

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("role", user.getRole());
        claims.put("name", user.getName());
        String token = JwtUtil.genToken(claims);

        //把token存储到redis中
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set(token, token, 1, TimeUnit.HOURS);
        return Result.success(token);
    }

    @GetMapping("/userInfo") //获取账号的基本信息
    public Result<User> getUser() {
        Map<String, Object> map = ThreadLocalUtil.get();
        User user = new User();
        user.setId((Integer) map.get("id"));
        user.setRole((Integer) map.get("role"));
        user.setName((String) map.get("name")) ;
        return Result.success(user);
    }

    @GetMapping("/exit") //退出当前账号
    public Result exit(@RequestHeader("Authorization") String token) {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);
        ThreadLocalUtil.remove();
        return Result.success("退出成功");
    }
}
