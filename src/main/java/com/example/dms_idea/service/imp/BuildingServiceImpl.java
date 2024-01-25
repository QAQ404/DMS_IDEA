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
    public PageBean<Building> getBuildingList(int pageNum, int pageSize, String prop, String order, String name, String unit_number, String dor_number, String stu_number, String manager_id) {
//        Map<String, Object> map = ThreadLocalUtil.get();
//        int id = (int) map.get("id");

        PageBean<Building> pageBean = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);

        List<Building> list = buildingMapper.getBuildingList(prop, order, name, unit_number, dor_number, stu_number, manager_id);

        Page<Building> pageBeanList = (Page<Building>) list;

        pageBean.setTotal(pageBeanList.getTotal());
        pageBean.setItems(pageBeanList.getResult());

        return pageBean;
    }

    @Override
    public Map<String, Object> ifNameHave(String name) {
        return buildingMapper.ifNameHave(name);
    }

    @Override
    public void addBuilding(String name, String unitNumber, String managerId) {
        buildingMapper.addBuilding(name, unitNumber, managerId);
    }

    @Override
    public void addBuildingInfo(int id) {
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

    @Override
    public List<Map<String, Object>> getOnlyName() {
        return buildingMapper.getOnlyName();
    }

    @Override
    public Map<String, Integer> getUnitAndFloor(String id) {
        return buildingMapper.getUnitAndFloor(id);
    }

    @Override
    public Map<String, Integer> getUnitAndFloor(Integer id) {
        return buildingMapper.getUnitAndFloor(id);
    }

    @Override
    public void addDormitoryNumber(int buildingId, int num) {
        buildingMapper.addDormitoryNumber(buildingId, num);
    }

    @Override
    public int getdorNumber(String id) {
        return buildingMapper.getdorNumber(id);
    }

    @Override
    public int getManId(String id) {
        return buildingMapper.getManId(id);
    }

    @Override
    public void deleteBuilding(String id) {
        buildingMapper.deleteBuildingInfo(id);
        buildingMapper.deleteBuilding(id);
    }
}
