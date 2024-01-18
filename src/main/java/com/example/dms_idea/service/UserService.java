package com.example.dms_idea.service;

import com.example.dms_idea.pojo.User;

public interface UserService {
    User getUser(String username, String password);
}
