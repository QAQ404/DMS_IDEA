package com.example.dms_idea.dao;

import com.example.dms_idea.pojo.ChangeDormitoryApplication;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ChangeDormitoryApplicationMapper {
    List<ChangeDormitoryApplication> getApplicationList(String prop, String order, Map<String, Object> map);

    void addApplication(Integer stuId, Integer newDorId, Integer newStuId, String newStuName, String newStudyId);
}
