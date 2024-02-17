package com.example.dms_idea.service;

import com.example.dms_idea.pojo.Major;
import com.example.dms_idea.pojo.PageBean;

import java.util.List;
import java.util.Map;

public interface MajorService {
    PageBean<Major> getMajorList(int pageNum, int pageSize, String prop, String order, Map<String, Object> map);

    Major getMajorByNameInsName(String name, String insName);

    void addMajor(String name, Integer insId);

    Major getMajorById(Integer id);

    void updateMajor(Major major);

    void deleteMajor(Integer id);

    List<Major> getMajorNameList(String insName);

    void addClazzNumber(Integer id, int i);

    void addStudentNumber(Integer id, int num);
}
