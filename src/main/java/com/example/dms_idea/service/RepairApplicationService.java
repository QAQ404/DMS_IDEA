package com.example.dms_idea.service;

import com.example.dms_idea.pojo.PageBean;
import com.example.dms_idea.pojo.RepairApplication;

import java.util.Map;

public interface RepairApplicationService {
    PageBean<RepairApplication> getApplicationList(int pageNum, int pageSize, String prop, String order, Map<String, Object> map);

    void addApplication(Integer stuId,Integer dorId,String troubleItem,String description,String picture);

    void deleteApplication(Integer id);

    void solveApplication(Integer id,Integer mangerId,String message);

    void updateAppState(Integer id,Integer state);
}
