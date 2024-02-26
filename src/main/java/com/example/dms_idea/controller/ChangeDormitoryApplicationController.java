package com.example.dms_idea.controller;

import com.example.dms_idea.pojo.ChangeDormitoryApplication;
import com.example.dms_idea.pojo.PageBean;
import com.example.dms_idea.pojo.Result;
import com.example.dms_idea.service.ChangeDormitoryApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/changeDormitoryApplication")
public class ChangeDormitoryApplicationController {

    @Autowired
    private ChangeDormitoryApplicationService changeDorAppService;

    @PostMapping("/getApplicationList")
    public Result<PageBean<ChangeDormitoryApplication>> getApplicationList(@RequestBody Map<String, Object> map){
        int pageNum = (int) map.get("pageNum");
        int pageSize = (int) map.get("pageSize");
        String prop = (String) map.get("prop");
        String order = (String) map.get("order");
        if (prop != null && order != null) {
            if (order.equals("ascending")) order = "asc";
            else if (order.equals("descending")) order = "desc";
            if (prop.equals("buildingName")) prop = "building_name";
            else if (prop.equals("unitNumber")) prop = "unit_number";
        }
        PageBean<ChangeDormitoryApplication> pageBean = changeDorAppService.getApplicationList(pageNum, pageSize, prop, order, map);
        return Result.success(pageBean);
    }
}
