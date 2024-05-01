package com.example.dms_idea.dao;

import com.example.dms_idea.pojo.RepairApplication;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RepairApplicationMapper {

    List<RepairApplication> getApplicationList(String prop, String order, Map<String, Object> map);

    void addApplication(Integer stuId, Integer dorId, String troubleItem, String description, String picture);

    void deleteApplication(Integer id);

    void solveApplication(Integer id, Integer mangerId, String message);

    void updateAppState(Integer id, Integer state);
}
