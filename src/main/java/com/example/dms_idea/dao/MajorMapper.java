package com.example.dms_idea.dao;

import com.example.dms_idea.pojo.Major;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MajorMapper {
    List<Major> getManagerList(String prop, String order, Map<String, Object> map);

    Major getMajorByNameInsId(String name, String insName);

    void addMajor(String name, Integer insId);

    Major getMajorById(Integer id);

    void updateMajor(Major major);

    void deleteMajor(Integer id);
}
