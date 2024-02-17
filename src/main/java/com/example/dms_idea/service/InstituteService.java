package com.example.dms_idea.service;

import com.example.dms_idea.pojo.Institute;
import com.example.dms_idea.pojo.PageBean;

import java.util.List;
import java.util.Map;

public interface InstituteService {
    PageBean<Institute> getInstituteList(int pageNum, int pageSize, String prop, String order, Map<String, Object> map);

    void addInstitute(String name);

    Institute getInstituteByName(String name);

    Institute getInstituteById(Integer id);

    void deleteInstitute(Integer id);

    void updateInstitute(Institute institute);

    List<Institute> getInstituteNameList();

    void addMajorNumber(Integer id, int i);

    void addClazzNumber(Integer id, int i);

    void addStudentNumber(Integer id, int num);
}
