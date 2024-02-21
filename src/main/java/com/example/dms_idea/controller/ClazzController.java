package com.example.dms_idea.controller;

import com.example.dms_idea.pojo.*;
import com.example.dms_idea.service.ClazzService;
import com.example.dms_idea.service.InstituteService;
import com.example.dms_idea.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @GetMapping("/getClazzListCascader")
    public Result<List<Map<String,Object>>> getClazzListCascader(){
        List<Institute> instituteList = instituteService.getInstituteNameList();
        List<Map<String,Object>> map = new ArrayList<>();
        for(Institute institute : instituteList){
            if(institute.getClazzNumber() == 0) continue;
            Map<String,Object> ins = new HashMap<>();
            ins.put("label",institute.getName());
            ins.put("value",institute.getId());
            List<Map<String,Object>> majorMap = new ArrayList<>();
            List<Major> majorList = majorService.getMajorNameList(institute.getName());
            for(Major major : majorList){
                if(major.getClazzNumber() == 0) continue;
                Map<String,Object> maj = new HashMap<>();
                maj.put("label",major.getName());
                maj.put("value",major.getId());
                List<Map<String,Object>> yearMap = new ArrayList<>();
                List<Integer> yearList = clazzService.getClazzYearByMajorInstitute(major.getName(),institute.getName());
                for(Integer year:yearList){
                    Map<String,Object> ye = new HashMap<>();
                    ye.put("label",year+"级");
                    ye.put("value",year);
                    List<Map<String,Object>> clazzMap = new ArrayList<>();
                    List<Clazz> clazzList = clazzService.getClazzListByMajorInstituteYear(major.getName(),institute.getName(),year);
                    for(Clazz clazz : clazzList){
                        Map<String,Object> cla = new HashMap<>();
                        cla.put("label",institute.getName()+major.getName()+clazz.getEntranceYear()+"级"+clazz.getName()+"班");
                        cla.put("value",clazz.getId());
                        clazzMap.add(cla);
                    }
                    ye.put("children",clazzMap);
                    yearMap.add(ye);
                }
                maj.put("children",yearMap);
                majorMap.add(maj);
            }
            ins.put("children",majorMap);
            map.add(ins);
        }
        return Result.success(map);
    }
}
