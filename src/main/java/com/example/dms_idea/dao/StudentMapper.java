package com.example.dms_idea.dao;

import com.example.dms_idea.pojo.Student;
import com.example.dms_idea.pojo.StudentInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    List<Student> getStudentList(String prop, String order, Map<String, Object> map, List<Integer> dormitoryList, List<Integer> clazzList);

    Student getStudentById(Integer id);

    List<Student> getStudentByDormitoryId(Integer id);

    void addStudent(int userId, int dormitoryId, String studyId, String gender, Integer insId, Integer majorId, Integer clazzId, String entranceYear);

    void addStudentInfo(Integer id);

    Student getSimpleStudentById(Integer id);

    void updateStudentInfo(StudentInfo student);

    void updateStudentGender(Integer id, String gender);

    void updateStudentEntranceYear(Integer id, String entranceYear);

    void updateStudentDormitoryId(Integer id, Integer dormitoryId);

    void updateStudentClazzId(Integer id, Integer clazzId);

    void updateStudentMajorId(Integer id, Integer majorId);

    void updateStudentInsId(Integer id, Integer insId);

    void deleteStudent(Integer id);

    void deleteStudentInfo(Integer id);
}
