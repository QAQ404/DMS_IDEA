package com.example.dms_idea.dao;

import com.example.dms_idea.pojo.Institute;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface InstituteMapper {

    List<Institute> getInstituteList(String prop, String order, Map<String, Object> map);

    void addInstitute(String name);

    Institute getInstituteByName(String name);

    Institute getInstituteById(Integer id);

    void deleteInstitute(Integer id);

    void updateInstitute(Institute institute);

    List<Institute> getInstituteNameList();

    void addMajorNumber(Integer id, int i);
}
