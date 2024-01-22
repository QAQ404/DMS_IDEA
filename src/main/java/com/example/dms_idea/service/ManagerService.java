package com.example.dms_idea.service;

import com.example.dms_idea.pojo.Manager;

import java.util.List;
import java.util.Map;

public interface ManagerService {
    List<Map<String,Object>> getAllManagerName();

    void addBuildingNumber(String managerId,int num);

    void addBuildingNumber(int managerId,int num);

}
