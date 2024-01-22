package com.example.dms_idea.controller;

import com.example.dms_idea.pojo.Building;
import com.example.dms_idea.pojo.PageBean;
import com.example.dms_idea.pojo.Result;
import com.example.dms_idea.service.BuildingService;
import com.example.dms_idea.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/building")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private ManagerService managerService;

    @GetMapping("/getBuildingList") //获取宿舍楼栋相关信息
    public Result<PageBean<Building>> getBuildingList(int pageNum, int pageSize, @RequestParam(required = false) String prop, @RequestParam(required = false) String order, @RequestParam(required = false) String name, @RequestParam(required = false) String unit_number, @RequestParam(required = false) String dor_number, @RequestParam(required = false) String stu_number, String manager_id) {

        if (prop != null && order != null) {
            if (order.equals("ascending")) order = "asc";
            else if (order.equals("descending")) order = "desc";
            if (prop.equals("unitNumber")) prop = "unit_number";
            else if (prop.equals("dorNumber")) prop = "dor_number";
            else if (prop.equals("stuNumber")) prop = "stu_number";
            else if (prop.equals("manName")) prop = "man_name";
        }

        PageBean<Building> pageBean = buildingService.getBuildingList(pageNum, pageSize, prop, order, name, unit_number, dor_number, stu_number, manager_id);
        return Result.success(pageBean);
    }

    @PostMapping("/addBuilding")    //添加宿舍楼
    public Result addBuilding(String name, String unitNumber, String managerId) {
        if (name == null || unitNumber == null || managerId == null) return Result.error("输入不能为空");
        if (name == "" || unitNumber == "" || managerId == "") return Result.error("输入不能为空");
        if (name.length() > 10) return Result.error("楼栋名称需少于10个字");
        if (buildingService.ifNameHave(name) != null) return Result.error("宿舍楼名字已存在");

        buildingService.addBuilding(name, unitNumber, managerId);
        int id = (int) buildingService.ifNameHave(name).get("id");
        buildingService.addBuildingInfo(id);
        managerService.addBuildingNumber(managerId, 1);
        return Result.success("添加成功");
    }

    @GetMapping("/getBuildingInfo") //获取某个宿舍楼的详细信息
    public Result<Building> getBuildingInfo(String buildingId) {
        Building building = buildingService.getBuildingInfo(buildingId);
        return Result.success(building);
    }

    @PutMapping("/updateBuildingInfo")  //修改宿舍楼信息
    public Result updateBuildingInfo(@RequestBody Map<String, Object> map) {
        //数据校验
        int id = (int) map.get("id");
        String name = (String) map.get("name");
        if (name.length() > 10) return Result.error("楼栋名称需少于10个字");
        if (name.length() == 0) return Result.error("楼栋名称不能为空");
        Map<String, Object> map1 = buildingService.ifNameHave(name);
        if (map1 != null && id != (int) map1.get("id")) return Result.error("宿舍楼名字已存在");

        buildingService.updateBuildingInfo(map);

        return Result.success("修改成功");
    }

    @DeleteMapping("/deleteBuilding")   //删除宿舍楼
    public Result deleteBuilding(String id) {
        //删除涉及到宿舍和学生，先不写

        return Result.success(id);
    }
}
