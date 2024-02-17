package com.example.dms_idea.dao;

import com.example.dms_idea.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    List<Student> getStudentList(String prop, String order, Map<String, Object> map);

    Student getStudentById(Integer id);

    void addStudent(int userId, int dormitoryId, String studyId, String gender, Integer insId, Integer majorId, Integer clazzId, String entranceYear);

    void addStudentInfo(Integer id);

    Student getSimpleStudentById(Integer id);
}
