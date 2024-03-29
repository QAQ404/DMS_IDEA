package com.example.dms_idea.service.imp;

import com.example.dms_idea.dao.StudentMapper;
import com.example.dms_idea.pojo.PageBean;
import com.example.dms_idea.pojo.Student;
import com.example.dms_idea.pojo.StudentInfo;
import com.example.dms_idea.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageBean<Student> getStudentList(int pageNum, int pageSize, String prop, String order, Map<String, Object> map) {
        PageBean<Student> pageBean = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);

        List<List> OldDormitoryList = (List<List>) map.get("dormitoryList");
        List<Integer> dormitoryList = new ArrayList<>();
        for(List li : OldDormitoryList){
            dormitoryList.add((Integer) li.get(3));
        }
        List<List> OldClazzList = (List<List>) map.get("clazzList");
        List<Integer> clazzList = new ArrayList<>();
        for(List cl : OldClazzList){
            clazzList.add((Integer) cl.get(3));
        }

        List<Student> list = studentMapper.getStudentList(prop, order,map,dormitoryList,clazzList);

        Page<Student> pageBeanList = (Page<Student>) list;

        pageBean.setTotal(pageBeanList.getTotal());
        pageBean.setItems(pageBeanList.getResult());
        return pageBean;
    }

    @Override
    public Student getStudentById(Integer id) {
        return studentMapper.getStudentById(id);
    }

    @Override
    public Student getSimpleStudentById(Integer id) {
        return studentMapper.getSimpleStudentById(id);
    }

    @Override
    public void addStudent(int userId, int dormitoryId, String studyId, String gender, Integer insId, Integer majorId, Integer clazzId, String entranceYear) {
        studentMapper.addStudent(userId,dormitoryId,studyId,gender,insId,majorId,clazzId,entranceYear);
    }

    @Override
    public void addStudentInfo(Integer id) {
        studentMapper.addStudentInfo(id);
    }

    @Override
    public void updateStudentInfo(StudentInfo studentInfo) {
        studentMapper.updateStudentInfo(studentInfo);
    }

    @Override
    public void updateStudentGender(Integer id, String gender) {
        studentMapper.updateStudentGender(id,gender);
    }

    @Override
    public void updateStudentEntranceYear(Integer id, String entranceYear) {
        studentMapper.updateStudentEntranceYear(id,entranceYear);
    }

    @Override
    public void updateStudentDormitoryId(Integer id, Integer dormitoryId) {
        studentMapper.updateStudentDormitoryId(id,dormitoryId);
    }

    @Override
    public void updateStudentClazzId(Integer id, Integer clazzId) {
        studentMapper.updateStudentClazzId(id,clazzId);
    }

    @Override
    public void updateStudentMajorId(Integer id, Integer majorId) {
        studentMapper.updateStudentMajorId(id,majorId);
    }

    @Override
    public void updateStudentInsId(Integer id, Integer insId) {
        studentMapper.updateStudentInsId(id,insId);
    }

    @Override
    public void deleteStudent(Integer id) {
        studentMapper.deleteStudentInfo(id);
        studentMapper.deleteStudent(id);
    }

    @Override
    public List<Student> getStudentByDormitoryId(int id) {
        return studentMapper.getStudentByDormitoryId(id);
    }


}
