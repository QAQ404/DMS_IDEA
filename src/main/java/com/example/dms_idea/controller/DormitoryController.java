package com.example.dms_idea.controller;

import com.example.dms_idea.pojo.Dormitory;
import com.example.dms_idea.pojo.PageBean;
import com.example.dms_idea.pojo.Result;
import com.example.dms_idea.service.BuildingService;
import com.example.dms_idea.service.DormitoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        }
        PageBean<Dormitory> pageBean = dormitoryService.getDormitoryList(pageNum, pageSize, prop, order,map);
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
        Map<String,Integer> res = buildingService.getUnitAndFloor(buildingId);
        if(res.get("unitNumber") < unitNumber) return Result.error("该楼栋最大单元是"+res.get("unitNumber"));
        if(res.get("floorNumber") < floorNumber) return Result.error("该楼栋最高楼层是"+res.get("floorNumber"));
        dormitoryService.addDormitory(bedNumber,buildingId,floorNumber,name,unitNumber);
        buildingService.addDormitoryNumber(buildingId,1);
        return Result.success();
    }

    @GetMapping("/getMaxUnitAndFloor")  //获取某栋宿舍楼中单元号和楼层的最大值
    public Result<Map<String,Integer>> getMaxUnitAndFloor(String id){
        Map<String,Integer> map = dormitoryService.getMaxUnitAndFloor(id);
        return Result.success(map);
    }

    @GetMapping("/getDormitoryById") //通过id查找寝室
    public Result<Dormitory> getDormitoryById(int id){
        Dormitory dormitory = dormitoryService.getDormitoryById(id);
        return Result.success(dormitory);
    }

    @PutMapping("/updateDormitory") //更新寝室信息
    public Result updateDormitory(@RequestBody Dormitory dormitory){
        Dormitory old = dormitoryService.getDormitoryById(dormitory.getId());
        if(!old.getName().equals(dormitory.getName()) || old.getFloorNumber()!=dormitory.getFloorNumber() || old.getUnitNumber()!=dormitory.getUnitNumber() || old.getBuildingId() != dormitory.getBuildingId()){
            if (dormitoryService.ifDormitoryNameHaveInTheSameUnitAndFloor(dormitory.getName(), dormitory.getUnitNumber()
                    , dormitory.getFloorNumber(), dormitory.getBuildingId()) > 0) return Result.error("此楼栋的此单元的此楼层存在重名寝室");
        }
        buildingService.addDormitoryNumber(dormitory.getBuildingId(),1);
        buildingService.addDormitoryNumber(old.getBuildingId(),-1);
        dormitoryService.updateDormitory(dormitory);
        return Result.success();
    }

    @DeleteMapping("/deleteDormitory") //删除寝室
    public Result deleteDormitory(String id){
        Dormitory dormitory = dormitoryService.getDormitoryById(id);
        if(dormitory.getStuNumber()>0) return Result.error("删除失败,该宿舍内住着学生");
        buildingService.addDormitoryNumber(dormitory.getBuildingId(),-1);
        dormitoryService.deleteDormitory(id);
        return Result.success();
    }

    @PostMapping("/checkDormitoryName") //查看某栋某单元某楼的全部寝室名称
    public Result<List<String>> checkDormitoryName(@RequestBody Map<String,Integer> map){
        List<String> res = dormitoryService.checkDormitoryName(map);
        return Result.success(res);
    }

    @PostMapping("/addDormitoryMany") //批量添加寝室
    public Result addDormitoryMany(@RequestBody Map<String,Object> map){
        int bedNumber = (int) map.get("bedNumber");
        int buildingId = (int) map.get("buildingId");
        int floorNumber = (int) map.get("floorNumber");
        int unitNumber = (int) map.get("unitNumber");
        List<String> list = (List<String>) map.get("nameList");
        for(String i : list){
            dormitoryService.addDormitory(bedNumber,buildingId,floorNumber,i,unitNumber);
            buildingService.addDormitoryNumber(buildingId,1);
        }
        return Result.success();
    }
}
