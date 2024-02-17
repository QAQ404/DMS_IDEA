package com.example.dms_idea.service;

import com.example.dms_idea.pojo.Clazz;
import com.example.dms_idea.pojo.PageBean;

import java.util.List;
import java.util.Map;

public interface ClazzService {
    PageBean<Clazz> getClazzList(int pageNum, int pageSize, String prop, String order, Map<String, Object> map);

    Clazz getClazzByName(Integer name, String insName, String majorName, Integer entranceYear);

    void addClazz(Integer name, Integer majorId, Integer entranceYear);

    Clazz getClazzById(Integer id);

    void updateClazz(Clazz clazz);

    void deleteClazz(Integer id);

    List<Clazz> getClazzList();

    List<Clazz> getClazzListByMajorInstitute(String majorName, String insName);

    void addStudentNumber(Integer id, int num);
}
