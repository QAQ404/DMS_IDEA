package com.example.dms_idea.dao;

import com.example.dms_idea.pojo.RepairApplication;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RepairApplicationMapper {

    List<RepairApplication> getApplicationList(String prop, String order, Map<String, Object> map);
}
