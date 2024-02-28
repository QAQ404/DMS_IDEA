package com.example.dms_idea.service.imp;

import com.example.dms_idea.dao.ChangeDormitoryApplicationMapper;
import com.example.dms_idea.pojo.ChangeDormitoryApplication;
import com.example.dms_idea.pojo.PageBean;
import com.example.dms_idea.service.ChangeDormitoryApplicationService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChangeDormitoryApplicationImpl implements ChangeDormitoryApplicationService {
    @Autowired
    private ChangeDormitoryApplicationMapper changeDorAppMapper;

    @Override
    public PageBean<ChangeDormitoryApplication> getApplicationList(int pageNum, int pageSize, String prop, String order, Map<String, Object> map) {
        PageBean<ChangeDormitoryApplication> pageBean = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);

        List<ChangeDormitoryApplication> list = changeDorAppMapper.getApplicationList(prop, order,map);

        Page<ChangeDormitoryApplication> pageBeanList = (Page<ChangeDormitoryApplication>) list;

        pageBean.setTotal(pageBeanList.getTotal());
        pageBean.setItems(pageBeanList.getResult());
        return pageBean;
    }

    @Override
    public void addApplication(Integer stuId,Integer oldDorId, Integer newDorId, Integer newStuId, String newStuName, String newStudyId) {
        changeDorAppMapper.addApplication(stuId,oldDorId,newDorId,newStuId,newStuName,newStudyId);
    }

    @Override
    public void deleteApplication(Integer id) {
        changeDorAppMapper.deleteApplication(id);
    }

    @Override
    public void updateStateById(Integer id, int state) {
        changeDorAppMapper.updateStateById(id,state);
    }

    @Override
    public ChangeDormitoryApplication getApplicationById(Integer id) {
        return changeDorAppMapper.getApplicationById(id);
    }

    @Override
    public void updateStateByNewStuId(Integer newStuId, int state) {
        changeDorAppMapper.updateStateByNewStuId(newStuId,state);
    }

    @Override
    public void updateStateByNewIdAndNewStuId(Integer newId, int newStuId, int state) {
        changeDorAppMapper.updateStateByNewIdAndNewStuId(newId,newStuId,state);
    }

    @Override
    public void updateStateByStuId(Integer stuId, int state) {
        changeDorAppMapper.updateStateByStuId(stuId,state);
    }

    @Override
    public void deleteApplicationByStuId(Integer stuId) {
        changeDorAppMapper.deleteApplicationByStuId(stuId);
    }

    @Override
    public void deleteApplicationByOldIdOrNewId(String id) {
        changeDorAppMapper.deleteApplicationByOldIdOrNewId(id);
    }
}
