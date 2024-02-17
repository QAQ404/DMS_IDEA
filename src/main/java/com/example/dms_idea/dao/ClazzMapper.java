package com.example.dms_idea.dao;

import com.example.dms_idea.pojo.Clazz;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClazzMapper {
    List<Clazz> getClazzList(String prop, String order, Map<String, Object> map);

    Clazz getClazzByName(Integer name, String insName, String majorName, Integer entranceYear);

    void addClazz(Integer name, Integer majorId, Integer entranceYear);

    Clazz getClazzById(Integer id);

    void updateClazz(Clazz clazz);

    void deleteClazz(Integer id);

    List<Clazz> getClazzListByMajorInstitute(String majorName, String insName);

    void addStudentNumber(Integer id, int num);
}
