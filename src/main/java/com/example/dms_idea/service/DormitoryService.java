package com.example.dms_idea.service;

import com.example.dms_idea.pojo.Dormitory;
import com.example.dms_idea.pojo.PageBean;

import java.util.Map;

public interface DormitoryService {
    PageBean<Dormitory> getDormitoryList(int pageNum, int pageSize, String prop, String order);

    void addDormitory(int bedNumber, int buildingId, int floorNumber, String name, int unitNumber);

    Integer ifDormitoryNameHaveInTheSameUnitAndFloor(String name, int unitNumber, int floorNumber, int buildingId);

    Map<String, Integer> getMaxUnitAndFloor(String id);

    Map<String, Integer> getMaxUnitAndFloor(Integer id);
}
