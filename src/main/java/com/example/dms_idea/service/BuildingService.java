package com.example.dms_idea.service;

import com.example.dms_idea.pojo.Building;
import com.example.dms_idea.pojo.PageBean;

import java.util.List;
import java.util.Map;

public interface BuildingService {

    PageBean<Building> getBuildingList(int pageNum, int pageSize, String prop, String order, String name, String unit_number, String dor_number, String stu_number, String manager_id);

    Map<String, Object> ifNameHave(String name);

    void addBuilding(String name, String valueOf, String valueOf1);

    void addBuildingInfo(int id);

    Building getBuildingInfo(String buildingId);

    void updateBuildingInfo(Map<String, Object> map);

    List<Map<String, Object>> getOnlyName();

    Map<String, Integer> getUnitAndFloor(String id);

    Map<String, Integer> getUnitAndFloor(Integer id);

    void addDormitoryNumber(int buildingId, int num);

    int getdorNumber(String id);

    int getManId(String id);

    void deleteBuilding(String id);

    void addStudentNumber(int id, int num);

    List<Building> getBuildingList();
}
