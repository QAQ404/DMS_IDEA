package com.example.dms_idea.dao;

import com.example.dms_idea.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User getUser(String username, String password);
}
