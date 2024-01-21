package com.example.dms_idea.service;

import com.example.dms_idea.pojo.Building;
import com.example.dms_idea.pojo.PageBean;

import java.util.Map;

public interface BuildingService {

    PageBean<Building> getBuildingList(int pageNum, int pageSize);

    String ifNameHave(String name);

    void addBuilding(String name, String valueOf, String valueOf1);

    void addBuildingInfo(String id);

    Building getBuildingInfo(String buildingId);

    void updateBuildingInfo(Map<String, Object> map);
}
