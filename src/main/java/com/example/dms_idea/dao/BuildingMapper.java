package com.example.dms_idea.dao;

import com.example.dms_idea.pojo.Building;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BuildingMapper {

    List<Building> getBuildingList(String prop, String order, String name, String unit_number, String dor_number, String stu_number, String manager_id);

    Map<String,Object> ifNameHave(String name);

    void addBuilding(String name, String unitNumber, String managerId);

    void addBuildingInfo(int id);

    Building getBuildingInfo(String buildingId);

    void updateBuildingInfo(Map<String, Object> map);

    List<Map<String,Object>> getOnlyName();

    Map<String, Integer> getUnitAndFloor(String id);

    Map<String, Integer> getUnitAndFloor(Integer id);

    void addDormitoryNumber(int buildingId, int num);

    int getdorNumber(String id);

    int getManId(String id);

    void deleteBuildingInfo(String id);

    void deleteBuilding(String id);

    void addStudentNumber(int id, int num);

    List<Building> getBuildingList();
}
