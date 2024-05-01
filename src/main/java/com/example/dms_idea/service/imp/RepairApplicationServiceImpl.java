package com.example.dms_idea.service.imp;

import com.example.dms_idea.dao.RepairApplicationMapper;
import com.example.dms_idea.pojo.PageBean;
import com.example.dms_idea.pojo.RepairApplication;
import com.example.dms_idea.service.RepairApplicationService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RepairApplicationServiceImpl implements RepairApplicationService {

    @Autowired
    private RepairApplicationMapper repairApplicationMapper;

    @Override
    public PageBean<RepairApplication> getApplicationList(int pageNum, int pageSize, String prop, String order, Map<String, Object> map) {
        PageBean<RepairApplication> pageBean = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);

        List<RepairApplication> list = repairApplicationMapper.getApplicationList(prop, order,map);

        Page<RepairApplication> pageBeanList = (Page<RepairApplication>) list;

        pageBean.setTotal(pageBeanList.getTotal());
        pageBean.setItems(pageBeanList.getResult());
        return pageBean;
    }

    @Override
    public void addApplication(Integer stuId, Integer dorId, String troubleItem, String description, String picture) {
        repairApplicationMapper.addApplication(stuId,dorId,troubleItem,description,picture);
    }

    @Override
    public void deleteApplication(Integer id) {
        repairApplicationMapper.deleteApplication(id);
    }

    @Override
    public void solveApplication(Integer id, Integer mangerId, String message) {
        repairApplicationMapper.solveApplication(id,mangerId,message);
    }

    @Override
    public void updateAppState(Integer id, Integer state) {
        repairApplicationMapper.updateAppState(id,state);
    }
}
