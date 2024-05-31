package com.example.dms_idea.service;

import com.example.dms_idea.pojo.Manager;
import com.example.dms_idea.pojo.PageBean;

import java.util.List;
import java.util.Map;

public interface ManagerService {
    List<Map<String,Object>> getAllManagerName();
    void addBuildingNumber(String managerId,int num);
    void addBuildingNumber(int managerId,int num);
    PageBean<Manager> getManagerList(int pageNum, int pageSize, String prop, String order,
                                     String name, String gender, String workId);
    boolean ifWorkIdHave(String workId);
    void addManager(String userId,String workId);
    void updateManager(Manager manager);
    Manager getManagerById(String id);
    Manager getManagerById(Integer id);
    void deleteManagerById(String id);
}
