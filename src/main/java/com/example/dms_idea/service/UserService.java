package com.example.dms_idea.service;

import com.example.dms_idea.pojo.Result;
import com.example.dms_idea.pojo.User;

public interface UserService {
    User getUser(String username, String password);

    Result addUser(String username, String password, String name, int role);
}
