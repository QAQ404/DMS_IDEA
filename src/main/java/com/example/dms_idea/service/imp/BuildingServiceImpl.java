package com.example.dms_idea.service.imp;

import com.example.dms_idea.dao.BuildingMapper;
import com.example.dms_idea.pojo.Building;
import com.example.dms_idea.pojo.PageBean;
import com.example.dms_idea.service.BuildingService;
import com.example.dms_idea.untils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingMapper buildingMapper;

    @Override
    public PageBean<Building> getBuildingList(int pageNum, int pageSize) {
        Map<String, Object> map = ThreadLocalUtil.get();
        int id = (int) map.get("id");

        PageBean<Building> pageBean = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);

        List<Building> list = buildingMapper.getBuildingList();
        Page<Building> pageBeanList = (Page<Building>) list;

        pageBean.setTotal(pageBeanList.getTotal());
        pageBean.setItems(pageBeanList.getResult());

        return pageBean;
    }

    @Override
    public String ifNameHave(String name) {
        return  buildingMapper.ifNameHave(name);
    }

    @Override
    public void addBuilding(String name, String unitNumber, String managerId) {
        buildingMapper.addBuilding(name, unitNumber, managerId);
    }

    @Override
    public void addBuildingInfo(String id) {
        buildingMapper.addBuildingInfo(id);
    }

    @Override
    public Building getBuildingInfo(String buildingId) {
        return buildingMapper.getBuildingInfo(buildingId);
    }

    @Override
    public void updateBuildingInfo(Map<String, Object> map) {
        buildingMapper.updateBuildingInfo(map);
    }
}
