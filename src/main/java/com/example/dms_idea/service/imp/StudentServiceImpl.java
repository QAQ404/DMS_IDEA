package com.example.dms_idea.service.imp;

import com.example.dms_idea.dao.StudentMapper;
import com.example.dms_idea.pojo.PageBean;
import com.example.dms_idea.pojo.Student;
import com.example.dms_idea.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        List<Student> list = studentMapper.getStudentList(prop, order,map);

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
}
