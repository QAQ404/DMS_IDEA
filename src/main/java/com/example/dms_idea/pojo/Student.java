package com.example.dms_idea.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer id;
    private String name;    //学生姓名
    private Integer buildingId; //楼栋Id
    private String buildingName; //楼栋名称
    private Integer dormitoryId; //寝室Id
    private String dormitoryName; //寝室名称
    private Integer unitNumber; //所在单元号
    private Integer floorNumber; //所在楼层
    private Integer insId; //学院Id
    private String insName; //学院名称
    private Integer majorId; //专业Id
    private String majorName; //专业名称
    private Integer clazzId; //班级Id
    private Integer clazzName; //班级号
    private Integer clazzYear; //班级年级
    private String gender; //学生性别
    private String studyId; //学号
    private Integer entranceYear;//入学年份
    private StudentInfo studentInfo;
}