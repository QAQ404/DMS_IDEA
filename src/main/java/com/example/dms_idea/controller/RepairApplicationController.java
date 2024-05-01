package com.example.dms_idea.controller;

import com.example.dms_idea.pojo.*;
import com.example.dms_idea.service.ManagerService;
import com.example.dms_idea.service.RepairApplicationService;
import com.example.dms_idea.service.StudentService;
import com.example.dms_idea.untils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/repairApplication")
public class RepairApplicationController {

    @Autowired
    private RepairApplicationService repairApplicationService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ManagerService managerService;

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

        List<Map<String, Object>> managerList = managerService.getAllManagerName();
        Map<Integer,String> manMap = new HashMap<>();
        for(Map<String, Object> map1:managerList){
            Integer manId = (Integer) map1.get("id");
            String manName = (String) map1.get("name");
            manMap.put(manId,manName);
        }
        for(RepairApplication repairApplication : pageBean.getItems()){
            if(repairApplication.getState()>0){
                repairApplication.setManagerName(manMap.get(repairApplication.getManagerId()));
            }
        }

        return Result.success(pageBean);
    }

    @PostMapping("/addApplication")
    public Result addApplication(@RequestBody Map<String, Object> map){
        Map<String, Object> user = ThreadLocalUtil.get();
        Integer stuId = (Integer) user.get("id");
        Student student = studentService.getStudentById(stuId);
        String troubleItem = (String) map.get("troubleItem");
        String description = (String) map.get("description");
        String picture = (String) map.get("picture");
        repairApplicationService.addApplication(stuId,student.getDormitoryId(),troubleItem,description,picture);
        return Result.success();
    }

    @PutMapping("/solveApplication")
    public Result solveApplication(@RequestBody Map<String, Object> map){
        Integer id = (Integer) map.get("id");
        Integer type = (Integer) map.get("type");
        String message = (String) map.get("message");
        Map<String, Object> user = ThreadLocalUtil.get();
        Integer manId = (Integer) user.get("id");
        repairApplicationService.updateAppState(id,type+1);
        repairApplicationService.solveApplication(id,manId,message);
        return Result.success();
    }

    @DeleteMapping("/deleteApplication")
    public Result deleteApplication(Integer id){
        repairApplicationService.deleteApplication(id);
        return Result.success();
    }
}
