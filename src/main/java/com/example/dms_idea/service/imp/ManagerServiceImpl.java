package com.example.dms_idea.service.imp;

import com.example.dms_idea.dao.ManagerMapper;
import com.example.dms_idea.pojo.Manager;
import com.example.dms_idea.service.ManagerService;
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

}
