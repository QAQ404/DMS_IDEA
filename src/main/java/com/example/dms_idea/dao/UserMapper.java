package com.example.dms_idea.dao;

import com.example.dms_idea.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User getUser(String username, String password);

    User ifUsernameHave(String username);

    void addUser(String username, String password, String name, int role);

    String getUserIdByUsername(String username);
}
