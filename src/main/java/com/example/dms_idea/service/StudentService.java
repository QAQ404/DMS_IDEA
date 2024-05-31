package com.example.dms_idea.service;

import com.example.dms_idea.pojo.PageBean;
import com.example.dms_idea.pojo.Student;
import com.example.dms_idea.pojo.StudentInfo;

import java.util.List;
import java.util.Map;

public interface StudentService {
    PageBean<Student> getStudentList(int pageNum, int pageSize, String prop,
                                     String order, Map<String, Object> map);
    Student getStudentById(Integer id);
    Student getSimpleStudentById(Integer id);
    void addStudent(int userId, int dormitoryId, String studyId,
                    String gender, Integer insId, Integer majorId, Integer clazzId, String entranceYear);
    void addStudentInfo(Integer id);
    void updateStudentInfo(StudentInfo studentInfo);
    void updateStudentGender(Integer id, String gender);
    void updateStudentEntranceYear(Integer id,String entranceYear);
    void updateStudentDormitoryId(Integer id, Integer dormitoryId);
    void updateStudentClazzId(Integer id, Integer clazzId);
    void updateStudentMajorId(Integer id, Integer majorId);
    void updateStudentInsId(Integer id, Integer insId);
    void deleteStudent(Integer id);
    List<Student> getStudentByDormitoryId(int id);
}
