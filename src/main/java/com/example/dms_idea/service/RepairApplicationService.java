package com.example.dms_idea.service;

import com.example.dms_idea.pojo.PageBean;
import com.example.dms_idea.pojo.RepairApplication;

import java.util.Map;

public interface RepairApplicationService {
    PageBean<RepairApplication> getApplicationList(int pageNum, int pageSize, String prop, String order, Map<String, Object> map);
}
