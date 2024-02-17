package com.example.dms_idea.service;

import com.example.dms_idea.pojo.PageBean;
import com.example.dms_idea.pojo.Student;

import java.util.Map;

public interface StudentService {
    PageBean<Student> getStudentList(int pageNum, int pageSize, String prop, String order, Map<String, Object> map);

    Student getStudentById(Integer id);

    Student getSimpleStudentById(Integer id);

    void addStudent(int userId, int dormitoryId, String studyId, String gender, Integer insId, Integer majorId, Integer clazzId, String entranceYear);

    void addStudentInfo(Integer id);
}
