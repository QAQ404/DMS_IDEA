package com.example.dms_idea.controller;

import com.example.dms_idea.pojo.Institute;
import com.example.dms_idea.pojo.Major;
import com.example.dms_idea.pojo.PageBean;
import com.example.dms_idea.pojo.Result;
import com.example.dms_idea.service.InstituteService;
import com.example.dms_idea.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/major")
public class MajorController {

    @Autowired
    private MajorService majorService;

    @Autowired
    private InstituteService instituteService;

    @PostMapping("/getMajorList")
    public Result<PageBean<Major>> getMajorList(@RequestBody Map<String ,Object> map){
        int pageNum = (int) map.get("pageNum");
        int pageSize = (int) map.get("pageSize");
        String prop = (String) map.get("prop");
        String order = (String) map.get("order");
        if (prop != null && order != null) {
            if (order.equals("ascending")) order = "asc";
            else if (order.equals("descending")) order = "desc";
            if (prop.equals("insName")) prop = "ins_name";
            else if (prop.equals("clazzNumber")) prop = "clazz_number";
            else if (prop.equals("stuNumber")) prop = "stu_number";
        }
        PageBean<Major> pageBean = majorService.getMajorList(pageNum, pageSize, prop, order,map);
        return Result.success(pageBean);
    }

    @PostMapping("/addMajor")
    public Result addMajor(String name,String insName){
        if(insName==null) return Result.error("学院不能为空");
        Major major = majorService.getMajorByNameInsName(name,insName);
        if(major != null) return Result.error("该学院已有此专业");
        Institute institute = instituteService.getInstituteByName(insName);
        majorService.addMajor(name,institute.getId());
        instituteService.addMajorNumber(institute.getId(),1);
        return Result.success();
    }

    @GetMapping("/getMajorNameList")
    public Result<List<Map<String,Object>>> getMajorNameList(String insName){
        List<Major> list = majorService.getMajorNameList(insName);
        List<Map<String,Object>> map = new ArrayList<>();
        for(Major i : list){
            Map<String,Object> res = new HashMap<>();
            res.put("label",i.getName());
            res.put("value",i.getId());
            map.add(res);
        }
        return Result.success(map);
    }

    @PutMapping("/updateMajor")
    public Result updateMajor(@RequestBody Major major){
        Major maj = majorService.getMajorByNameInsName(major.getName(),major.getInsName());
        if(maj!=null) return Result.error("该学院已存在此专业");
       majorService.updateMajor(major);
        return Result.success();
    }

    @DeleteMapping("/deleteMajor")
    public Result deleteMajor(Integer id){
        Major major = majorService.getMajorById(id);
        if(major.getClazzNumber()>0) return Result.error("该学院内仍存有班级");
        Institute institute = instituteService.getInstituteByName(major.getInsName());
        instituteService.addMajorNumber(institute.getId(),-1);

        majorService.deleteMajor(id);
        return Result.success();
    }

    @GetMapping("/getMajorById")
    public Result<Major> getMajorById(Integer id){
        Major major = majorService.getMajorById(id);
        return Result.success(major);
    }

}
