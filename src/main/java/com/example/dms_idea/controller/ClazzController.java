package com.example.dms_idea.controller;

import com.example.dms_idea.pojo.*;
import com.example.dms_idea.service.ClazzService;
import com.example.dms_idea.service.InstituteService;
import com.example.dms_idea.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/clazz")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @Autowired
    private MajorService majorService;

    @Autowired
    private InstituteService instituteService;

    @PostMapping("/getClazzList")
    public Result<PageBean<Clazz>> getClazzList(@RequestBody Map<String ,Object> map){
        int pageNum = (int) map.get("pageNum");
        int pageSize = (int) map.get("pageSize");
        String prop = (String) map.get("prop");
        String order = (String) map.get("order");
        if (prop != null && order != null) {
            if (order.equals("ascending")) order = "asc";
            else if (order.equals("descending")) order = "desc";
            if (prop.equals("insName")) prop = "ins_name";
            else if (prop.equals("majorName")) prop = "major_name";
            else if (prop.equals("stuNumber")) prop = "stu_number";
            else if (prop.equals("entranceYear")) prop = "entrance_year";
        }
        PageBean<Clazz> pageBean = clazzService.getClazzList(pageNum, pageSize, prop, order,map);
        return Result.success(pageBean);
    }

    @PostMapping("/addClazz")
    public Result addClazz(Integer name,String insName,String majorName,Integer entranceYear){
        Clazz clazz = clazzService.getClazzByName(name,insName,majorName,entranceYear);
        if(clazz!=null) return Result.error("该班级已存在");
        Major major = majorService.getMajorByNameInsName(majorName,insName);
        clazzService.addClazz(name,major.getId(),entranceYear);
        majorService.addClazzNumber(major.getId(),1);
        Institute institute = instituteService.getInstituteByName(insName);
        instituteService.addClazzNumber(institute.getId(),1);
        return Result.success();
    }

    @GetMapping("/getClazzById")
    public Result<Clazz> getClazzById(Integer id){
        Clazz clazz = clazzService.getClazzById(id);
        return Result.success(clazz);
    }

    @PutMapping("/updateClazz")
    public Result updateClazz(@RequestBody Clazz clazz){
        Clazz cla = clazzService.getClazzByName(clazz.getName(),clazz.getInsName(),clazz.getMajorName(), clazz.getEntranceYear());
        if(cla!=null) return Result.error("已经存在该班级");
        clazzService.updateClazz(clazz);
        return Result.success();
    }

    @DeleteMapping("/deleteClazz")
    public Result deleteClazz(Integer id){
        Clazz clazz = clazzService.getClazzById(id);
        if(clazz.getStuNumber()>0) return Result.error("该班级中仍存在学生");
        clazzService.deleteClazz(id);
        Major major = majorService.getMajorByNameInsName(clazz.getMajorName(),clazz.getInsName());
        majorService.addClazzNumber(major.getId(),-1);
        Institute institute = instituteService.getInstituteByName(clazz.getInsName());
        instituteService.addClazzNumber(institute.getId(),-1);
        return Result.success();
    }
}
