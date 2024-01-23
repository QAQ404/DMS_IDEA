package com.example.dms_idea.controller;

import com.example.dms_idea.pojo.Building;
import com.example.dms_idea.pojo.Manager;
import com.example.dms_idea.pojo.PageBean;
import com.example.dms_idea.pojo.Result;
import com.example.dms_idea.service.ManagerService;
import com.example.dms_idea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private UserService userService;

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

    @PostMapping("/getManagerList")
    public Result<PageBean<Manager>> getManagerList(@RequestBody Map<String,Object>map){

        int pageNum = (int) map.get("pageNum");
        int pageSize = (int) map.get("pageSize");
        String prop = (String) map.get("prop");
        String order = (String) map.get("order");
        if (prop != null && order != null) {
            if (order.equals("ascending")) order = "asc";
            else if (order.equals("descending")) order = "desc";
            if(prop.equals("buildingNumber")) prop = "building_number";
            else if(prop.equals("workId")) prop = "work_id";
        }
        PageBean<Manager> pageBean = managerService.getManagerList(pageNum,pageSize,prop,order);
        return Result.success(pageBean);
    }

    @PostMapping("/addManager")
    public Result addManager(String name,String password,String rePassword,String username,String workId){
        if(true){
            if(name == null || password == null || rePassword == null || username == null || workId == null) return  Result.error("输入不能为空");
            if(name == "" || password == "" || rePassword == "" || username == "" || workId == "") return  Result.error("输入不能为空");
            if(!password.equals(rePassword) ) return Result.error("两次密码输入不一致");
            if(username.length()>20) return Result.error("账号长度需不超过20位");
            if(password.length()>32) return Result.error("密码长度需不超过32位");
            if(name.length()>20) return Result.error("姓名长度需不超过20位");
            if(workId.length()>20) return Result.error("工作编号需不超过20位");
        }

        if(managerService.ifWorkIdHave(workId)) return Result.error("工作编号已存在");

        Result result = userService.addUser(username,password,name,2);
        if(result.getCode() == 0) {
            managerService.addManager((String) result.getData(),workId);
            return Result.success();
        }else{
            return Result.error(result.getMessage());
        }
    }

    @PutMapping("/updateManager")
    public Result updateManager(@RequestBody Manager manager,String old){
        if(true){
            if(manager.getName() == null || manager.getWorkId() == null) return Result.error("编号和姓名不能为空");
            if(manager.getName() == "" || manager.getWorkId() == "") return Result.error("编号和姓名不能为空");
            if(manager.getName().length()>20) return Result.error("姓名长度需不超过20位");
            if(manager.getWorkId().length()>20) return Result.error("工作编号需不超过20位");
            if(manager.getPhone().length() > 11) return Result.error("手机号码长度不能超过11位");
            if(manager.getPhone().length() > 128) return Result.error("邮箱长度需小于128位");
        }
        return Result.success();
    }
}
