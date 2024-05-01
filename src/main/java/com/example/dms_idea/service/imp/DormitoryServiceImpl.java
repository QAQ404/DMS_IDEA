package com.example.dms_idea.service.imp;

import com.example.dms_idea.dao.DormitoryMapper;
import com.example.dms_idea.pojo.Dormitory;
import com.example.dms_idea.pojo.PageBean;
import com.example.dms_idea.service.DormitoryService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DormitoryServiceImpl implements DormitoryService {

    @Autowired
    private DormitoryMapper dormitoryMapper;

    @Override
    public PageBean<Dormitory> getDormitoryList(int pageNum, int pageSize, String prop, String order, Map<String, Object> map) {
        PageBean<Dormitory> pageBean = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);

        List<Dormitory> list = dormitoryMapper.getDormitoryList(prop, order,map);

        Page<Dormitory> pageBeanList = (Page<Dormitory>) list;

        pageBean.setTotal(pageBeanList.getTotal());
        pageBean.setItems(pageBeanList.getResult());
        return pageBean;
    }

    @Override
    public void addDormitory(int bedNumber, int buildingId, int floorNumber, String name, int unitNumber) {
        dormitoryMapper.addDormitory(bedNumber, buildingId, floorNumber, name, unitNumber);
    }

    @Override
    public Integer ifDormitoryNameHaveInTheSameUnitAndFloor(String name, int unitNumber, int floorNumber, int buildingId) {
        return dormitoryMapper.ifDormitoryNameHaveInTheSameUnitAndFloor(name, unitNumber, floorNumber, buildingId);
    }

    @Override
    public Map<String, Integer> getMaxUnitAndFloor(String id) {
        Map<String,Integer> map = dormitoryMapper.getMaxUnitAndFloor(id);
        if(map == null){
            map = new HashMap<>();
            map.put("msg",1);
            map.put("unitNumber",1);
            map.put("floorNumber",1);
        }else map.put("msg",0);
        return map;
    }
    @Override
    public Map<String, Integer> getMaxUnitAndFloor(Integer id) {
        Map<String,Integer> map = dormitoryMapper.getMaxUnitAndFloor(id);
        if(map == null){
            map = new HashMap<>();
            map.put("msg",1);
            map.put("unitNumber",1);
            map.put("floorNumber",1);
        }else map.put("msg",0);
        return map;
    }

    @Override
    public Dormitory getDormitoryById(int id) {
        return dormitoryMapper.getDormitoryById(id);
    }
    @Override
    public Dormitory getDormitoryById(String id) {
        return dormitoryMapper.getDormitoryById(id);
    }

    @Override
    public void updateDormitory(Dormitory dormitory) {
        dormitoryMapper.updateDormitory(dormitory);
    }

    @Override
    public void deleteDormitory(String id) {
        dormitoryMapper.deleteDormitory(id);
    }

    @Override
    public List<String> checkDormitoryName(Map<String, Integer> map) {
        return dormitoryMapper.checkDormitoryName(map);
    }

    @Override
    public void addStudentNumber(int id, int num) {
        dormitoryMapper.addStudentNumber(id,num);
    }

    @Override
    public List<Dormitory> getDormitoryListByBuildingIdUnitFloor(int id, int unit, int floor) {
        return dormitoryMapper.getDormitoryListByBuildingIdUnitFloor(id,unit,floor);
    }

    @Override
    public List<Dormitory> getDormitoryListByBuildingIdUnitFloorHasStu(int id, int unit, int floor) {
        return dormitoryMapper.getDormitoryListByBuildingIdUnitFloorHasStu(id,unit,floor);
    }

    @Override
    public List<Integer> getDormitoryUnitHasDor(int buildingId) {
        return dormitoryMapper.getDormitoryUnitHasDor(buildingId);
    }

    @Override
    public List<Integer> getDormitoryFlootHasDor(int buildingId, Integer unit) {
        return dormitoryMapper.getDormitoryFlootHasDor(buildingId,unit);
    }

    @Override
    public List<Integer> getDormitoryUnitHasStu(int buildingId) {
        return dormitoryMapper.getDormitoryUnitHasStu(buildingId);
    }

    @Override
    public List<Integer> getDormitoryFlootHasStu(int buildingId, Integer unit) {
        return dormitoryMapper.getDormitoryFlootHasStu(buildingId,unit);
    }
}
