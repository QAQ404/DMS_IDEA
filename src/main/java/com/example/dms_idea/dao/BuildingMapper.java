package com.example.dms_idea.dao;

import com.example.dms_idea.pojo.Building;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BuildingMapper {

    List<Building> getBuildingList();

    String ifNameHave(String name);

    void addBuilding(String name, String unitNumber, String managerId);

    void addBuildingInfo(String id);
}
