package com.example.dms_idea.service;

import com.example.dms_idea.pojo.ChangeDormitoryApplication;
import com.example.dms_idea.pojo.PageBean;

import java.util.Map;

public interface ChangeDormitoryApplicationService {
    PageBean<ChangeDormitoryApplication> getApplicationList(int pageNum, int pageSize,
                                                            String prop, String order, Map<String, Object> map);
    void addApplication(Integer stuId,Integer oldDorId ,Integer newDorId,
                        Integer newStuId,String newStuName, String newStudyId);
    void deleteApplication(Integer id);
    void updateStateById(Integer id, int state);
    ChangeDormitoryApplication getApplicationById(Integer id);
    void updateStateByNewStuId(Integer newStuId, int state);
    void updateStateByNewIdAndNewStuId(Integer newId, int newStuId, int state);
    void updateStateByStuId(Integer stuId, int state);
    void deleteApplicationByStuId(Integer stuId);
    void deleteApplicationByOldIdOrNewId(String id);
}
