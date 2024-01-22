package com.example.dms_idea.controller;

import com.example.dms_idea.pojo.Manager;
import com.example.dms_idea.pojo.Result;
import com.example.dms_idea.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping("/getAllManagerName")   //获取全部管理员的工作编号和姓名
    public Result<List<Map<String, Object>>> getAllManagerName() {
        List<Map<String, Object>> list = managerService.getAllManagerName();

        List<Map<String, Object>> ans = new ArrayList<>();
        for(Map item : list){
            String name = (String) item.get("name");
            int id = (int) item.get("id");
            String workId = (String) item.get("workId");
            Map<String,Object> res = new HashMap<>();
            res.put("label","编号"+workId+"-"+name);
            res.put("value",id);
            ans.add(res);
        }
        return Result.success(ans);
    }

    @PatchMapping("/changeBuildingNumber") //oldV的管理员-1，newV的管理员+1
    public Result changeBuildingNumber(int oldV,int newV){
        managerService.addBuildingNumber(oldV,-1);
        managerService.addBuildingNumber(newV,1);
        return Result.success();
    }

}
