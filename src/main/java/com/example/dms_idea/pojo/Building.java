package com.example.dms_idea.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Building {
    private int id; //宿舍楼的编号
    private String name;    //宿舍楼的名字
    private int unitNumber; //宿舍单元数
    private int dorNumber; //宿舍寝室数
    private int stuNumber; //宿舍楼学生数
    private String manName; //宿管名字
    private Date build_year;   //宿舍楼建设日期
    private String introduction;    //宿舍楼的介绍
    private String picture;     //宿舍楼的照片
}
