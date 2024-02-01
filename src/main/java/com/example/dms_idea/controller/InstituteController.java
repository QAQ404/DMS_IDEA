package com.example.dms_idea.controller;

import com.example.dms_idea.pojo.Institute;
import com.example.dms_idea.pojo.PageBean;
import com.example.dms_idea.pojo.Result;
import com.example.dms_idea.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/institute")
public class InstituteController {

    @Autowired
    private InstituteService instituteService;

    @PostMapping("/getInstituteList")
    public Result<PageBean<Institute>> getInstituteList(@RequestBody Map<String ,Object> map){
        int pageNum = (int) map.get("pageNum");
        int pageSize = (int) map.get("pageSize");
        String prop = (String) map.get("prop");
        String order = (String) map.get("order");
        if (prop != null && order != null) {
            if (order.equals("ascending")) order = "asc";
            else if (order.equals("descending")) order = "desc";
            if (prop.equals("majorNumber")) prop = "major_number";
            else if (prop.equals("clazzNumber")) prop = "clazz_number";
            else if (prop.equals("stuNumber")) prop = "stu_number";
        }
        PageBean<Institute> pageBean = instituteService.getInstituteList(pageNum, pageSize, prop, order,map);
        return Result.success(pageBean);
    }

    @PostMapping("/addInstitute")
    public Result addInstitute(String name){
        Institute institute = instituteService.getInstituteByName(name);
        if(institute!=null) return Result.error("已存在此学院");
        instituteService.addInstitute(name);
        return Result.success();
    }

    @GetMapping("/getInstituteName")
    public Result<Institute> getInstituteName(Integer id){
        Institute institute = instituteService.getInstituteById(id);
        return Result.success(institute);
    }

    @PutMapping("/updateInstitute")
    public Result updateInstitute(@RequestBody Institute institute){
        Institute ins = instituteService.getInstituteByName(institute.getName());
        if(ins!=null) return Result.error("已存在此学院");
        instituteService.updateInstitute(institute);
        return Result.success();
    }

    @DeleteMapping("/deleteInstitute")
    public Result deleteInstitute(Integer id){
        Institute institute = instituteService.getInstituteById(id);
        if(institute.getMajorNumber()>0) return Result.error("该学院内仍存有专业");

        instituteService.deleteInstitute(id);
        return Result.success();
    }

    @GetMapping("/getInstituteNameList")
    public Result<List<Map<String,Object>>> getInstituteNameList(){
        List<Institute> list = instituteService.getInstituteNameList();
        List<Map<String,Object>> map = new ArrayList<>();
        for(Institute i : list){
            Map<String,Object> res = new HashMap<>();
            res.put("label",i.getName());
            res.put("value",i.getId());
            map.add(res);
        }
        return Result.success(map);
    }
}
