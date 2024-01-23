package com.example.dms_idea.service.imp;

import com.example.dms_idea.dao.ManagerMapper;
import com.example.dms_idea.pojo.Manager;
import com.example.dms_idea.pojo.PageBean;
import com.example.dms_idea.service.ManagerService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public List<Map<String,Object>> getAllManagerName() {
        return managerMapper.getAllManagerName();
    }

    @Override
    public void addBuildingNumber(String managerId,int num) {
        managerMapper.addBuildingNumber(managerId,num);
    }

    @Override
    public void addBuildingNumber(int managerId,int num) {
        managerMapper.addBuildingNumber(managerId,num);
    }

    @Override
    public PageBean<Manager> getManagerList(int pageNum, int pageSize, String prop, String order) {
        PageBean<Manager> pageBean = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);

        List<Manager> list = managerMapper.getManagerList(prop,order);

        Page<Manager> pageBeanList = (Page<Manager>) list;

        pageBean.setTotal(pageBeanList.getTotal());
        pageBean.setItems(pageBeanList.getResult());
        return pageBean;
    }

    @Override
    public boolean ifWorkIdHave(String workId) {
        String id = managerMapper.ifWorkIdHave(workId);
        if(id == null) return false;
        return true;
    }

    @Override
    public void addManager(String userId,String workId) {
        managerMapper.addManager(userId,workId);
    }

}
