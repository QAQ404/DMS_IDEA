package com.example.dms_idea.controller;

import com.example.dms_idea.pojo.Building;
import com.example.dms_idea.pojo.PageBean;
import com.example.dms_idea.pojo.Result;
import com.example.dms_idea.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/building")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @GetMapping("/getBuildingList") //获取宿舍楼栋相关信息
    public Result<PageBean<Building>> getBuildingList(int pageNum,int pageSize){
        PageBean<Building> pageBean = buildingService.getBuildingList(pageNum,pageSize);
        return Result.success(pageBean);
    }

    @PostMapping("/addBuilding")    //添加宿舍楼
    public Result addBuilding(String name,String unitNumber,String managerId){
        if(name == null || unitNumber == null || managerId == null) return Result.error("输入不能为空");
        if(name == "" || unitNumber == "" || managerId == "") return Result.error("输入不能为空");
        if(buildingService.ifNameHave(name)!=null) return Result.error("宿舍楼名字已存在");

        buildingService.addBuilding(name,unitNumber,managerId);
        String id = buildingService.ifNameHave(name);
        buildingService.addBuildingInfo(id);

        return Result.success("添加成功");
    }
}
