package com.example.dms_idea.dao;

import com.example.dms_idea.pojo.Manager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ManagerMapper {
    List<Map<String,Object>> getAllManagerName();
}
