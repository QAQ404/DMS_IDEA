package com.example.dms_idea.dao;

import com.example.dms_idea.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User getUser(String username, String password);

    User ifUsernameHave(String username);

    void addUser(String username, String password, String name, int role);

    String getUserIdByUsername(String username);

    void updateName(int id, String name);

    void updatePassword(String id, String password);

    void deleteUserById(String id);

    void deleteUserById(Integer id);

    int getUserRoleById(String id);

    int getUserRoleById(Integer id);

    User getUserByUsername(String username);
}
