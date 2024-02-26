package com.example.dms_idea.service;

import com.example.dms_idea.pojo.ChangeDormitoryApplication;
import com.example.dms_idea.pojo.PageBean;

import java.util.Map;

public interface ChangeDormitoryApplicationService {

    PageBean<ChangeDormitoryApplication> getApplicationList(int pageNum, int pageSize, String prop, String order, Map<String, Object> map);
}
