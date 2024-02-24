package com.example.dms_idea.controller;

import com.example.dms_idea.pojo.Building;
import com.example.dms_idea.pojo.Dormitory;
import com.example.dms_idea.pojo.PageBean;
import com.example.dms_idea.pojo.Result;
import com.example.dms_idea.service.BuildingService;
import com.example.dms_idea.service.DormitoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dormitory")
public class DormitoryController {

    @Autowired
    private DormitoryService dormitoryService;

    @Autowired
    private BuildingService buildingService;

    @PostMapping("/getDormitoryList")   //获取全部寝室信息
    public Result<PageBean<Dormitory>> getDormitoryList(@RequestBody Map<String, Object> map) {
        int pageNum = (int) map.get("pageNum");
        int pageSize = (int) map.get("pageSize");
        String prop = (String) map.get("prop");
        String order = (String) map.get("order");
        if (prop != null && order != null) {
            if (order.equals("ascending")) order = "asc";
            else if (order.equals("descending")) order = "desc";
            if (prop.equals("buildingName")) prop = "building_name";
            else if (prop.equals("unitNumber")) prop = "unit_number";
            else if (prop.equals("floorNumber")) prop = "floor_number";
            else if (prop.equals("stuNumber")) prop = "stu_number";
            else if (prop.equals("manName")) prop = "man_name";
            else if (prop.equals("bedNumber")) prop = "bed_number-stu_number";
        }
        PageBean<Dormitory> pageBean = dormitoryService.getDormitoryList(pageNum, pageSize, prop, order, map);
        return Result.success(pageBean);
    }

    @PostMapping("/addDormitory")  //添加寝室
    public Result addDormitory(@RequestBody Map<String, Object> map) {
        String name = (String) map.get("name");
        if (name == null || name == "" || map.get("buildingId") == null) return Result.error("输入不能为空");
        if (name.length() > 10) return Result.error("寝室名称需不超过10位");
        int bedNumber = (int) map.get("bedNumber");
        int buildingId = (int) map.get("buildingId");
        int floorNumber = (int) map.get("floorNumber");
        int unitNumber = (int) map.get("unitNumber");
        if (dormitoryService.ifDormitoryNameHaveInTheSameUnitAndFloor(name, unitNumber, floorNumber, buildingId) > 0)
            return Result.error("此楼栋的此单元的此楼层存在重名寝室");
        Map<String, Integer> res = buildingService.getUnitAndFloor(buildingId);
        if (res.get("unitNumber") < unitNumber) return Result.error("该楼栋最大单元是" + res.get("unitNumber"));
        if (res.get("floorNumber") < floorNumber) return Result.error("该楼栋最高楼层是" + res.get("floorNumber"));
        dormitoryService.addDormitory(bedNumber, buildingId, floorNumber, name, unitNumber);
        buildingService.addDormitoryNumber(buildingId, 1);
        return Result.success();
    }

    @GetMapping("/getMaxUnitAndFloor")  //获取某栋宿舍楼中单元号和楼层的最大值
    public Result<Map<String, Integer>> getMaxUnitAndFloor(String id) {
        Map<String, Integer> map = dormitoryService.getMaxUnitAndFloor(id);
        return Result.success(map);
    }

    @GetMapping("/getDormitoryById") //通过id查找寝室
    public Result<Dormitory> getDormitoryById(int id) {
        Dormitory dormitory = dormitoryService.getDormitoryById(id);
        return Result.success(dormitory);
    }

    @PutMapping("/updateDormitory") //更新寝室信息
    public Result updateDormitory(@RequestBody Dormitory dormitory) {
        Dormitory old = dormitoryService.getDormitoryById(dormitory.getId());
        if (!old.getName().equals(dormitory.getName()) || old.getFloorNumber() != dormitory.getFloorNumber() || old.getUnitNumber() != dormitory.getUnitNumber() || old.getBuildingId() != dormitory.getBuildingId()) {
            if (dormitoryService.ifDormitoryNameHaveInTheSameUnitAndFloor(dormitory.getName(), dormitory.getUnitNumber()
                    , dormitory.getFloorNumber(), dormitory.getBuildingId()) > 0)
                return Result.error("此楼栋的此单元的此楼层存在重名寝室");
        }
        buildingService.addDormitoryNumber(dormitory.getBuildingId(), 1);
        buildingService.addDormitoryNumber(old.getBuildingId(), -1);
        dormitoryService.updateDormitory(dormitory);
        return Result.success();
    }

    @DeleteMapping("/deleteDormitory") //删除寝室
    public Result deleteDormitory(String id) {
        Dormitory dormitory = dormitoryService.getDormitoryById(id);
        if (dormitory.getStuNumber() > 0) return Result.error("删除失败,该宿舍内住着学生");
        buildingService.addDormitoryNumber(dormitory.getBuildingId(), -1);
        dormitoryService.deleteDormitory(id);
        return Result.success();
    }

    @PostMapping("/checkDormitoryName") //查看某栋某单元某楼的全部寝室名称
    public Result<List<String>> checkDormitoryName(@RequestBody Map<String, Integer> map) {
        List<String> res = dormitoryService.checkDormitoryName(map);
        return Result.success(res);
    }

    @PostMapping("/addDormitoryMany") //批量添加寝室
    public Result addDormitoryMany(@RequestBody Map<String, Object> map) {
        int bedNumber = (int) map.get("bedNumber");
        int buildingId = (int) map.get("buildingId");
        int floorNumber = (int) map.get("floorNumber");
        int unitNumber = (int) map.get("unitNumber");
        List<String> list = (List<String>) map.get("nameList");
        for (String i : list) {
            dormitoryService.addDormitory(bedNumber, buildingId, floorNumber, i, unitNumber);
            buildingService.addDormitoryNumber(buildingId, 1);
        }
        return Result.success();
    }

    @GetMapping("/getDormitoryListCascader")
    public Result<List<Map<String, Object>>> getDormitoryListCascader() {
        List<Map<String, Object>> map = new ArrayList<>();
        List<Building> buildingList = buildingService.getBuildingList();
        for (Building building : buildingList) {
            if (building.getDorNumber() == 0) continue;
            Map<String, Object> buil = new HashMap<>();
            buil.put("label", building.getName());
            buil.put("value", building.getId());
            List<Map<String, Object>> buildingMap = new ArrayList<>();
            List<Integer> unitList = dormitoryService.getDormitoryUnitHasDor(building.getId());
            for (Integer unit : unitList) {
                Map<String, Object> un = new HashMap<>();
                un.put("label", unit + "单元");
                un.put("value", unit);
                List<Map<String, Object>> unitMap = new ArrayList<>();
                List<Integer> floorList = dormitoryService.getDormitoryFlootHasDor(building.getId(), unit);
                for (Integer floor : floorList) {
                    Map<String, Object> fl = new HashMap<>();
                    fl.put("label", floor + "楼");
                    fl.put("value", floor);
                    List<Map<String, Object>> floorMap = new ArrayList<>();
                    List<Dormitory> dormitoryList = dormitoryService.getDormitoryListByBuildingIdUnitFloor(building.getId(), unit, floor);
                    for (Dormitory dormitory : dormitoryList) {
                        Map<String, Object> dor = new HashMap<>();
                        dor.put("label", building.getName() + unit + "单元" + floor + "楼" + dormitory.getName());
                        dor.put("value", dormitory.getId());
                        dor.put("bed", dormitory.getBedNumber() - dormitory.getStuNumber());
                        if (dormitory.getBedNumber() - dormitory.getStuNumber() == 0) dor.put("disabled", true);
                        else dor.put("disabled", false);
                        floorMap.add(dor);
                    }
                    fl.put("children", floorMap);
                    unitMap.add(fl);
                }
                un.put("children", unitMap);
                buildingMap.add(un);
            }
            buil.put("children", buildingMap);
            map.add(buil);
        }
        return Result.success(map);
    }
}
