package com.example.dms_idea.controller;

import com.example.dms_idea.pojo.PageBean;
import com.example.dms_idea.pojo.RepairApplication;
import com.example.dms_idea.pojo.Result;
import com.example.dms_idea.service.RepairApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/repairApplication")
public class RepairApplicationController {

    @Autowired
    private RepairApplicationService repairApplicationService;

    @PostMapping("/getApplicationList")
    public Result<PageBean<RepairApplication>> getApplicationList(@RequestBody Map<String, Object> map){
        int pageNum = (int) map.get("pageNum");
        int pageSize = (int) map.get("pageSize");
        String prop = (String) map.get("prop");
        String order = (String) map.get("order");
        if (prop != null && order != null) {
            if (order.equals("ascending")) order = "asc";
            else if (order.equals("descending")) order = "desc";
            if (prop.equals("createTime")) prop = "create_time";
            else if (prop.equals("troubleItem")) prop = "trouble_item";
        }
        PageBean<RepairApplication> pageBean = repairApplicationService.getApplicationList(pageNum, pageSize, prop, order, map);
        return Result.success(pageBean);
    }


}
