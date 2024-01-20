package com.example.dms_idea.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id; //账号的编号
    private int role;//1为学生，2为普通宿管3为系统管理员
    private String name; //用户姓名
}
