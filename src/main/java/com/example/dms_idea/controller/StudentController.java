package com.example.dms_idea.controller;

import com.example.dms_idea.pojo.PageBean;
import com.example.dms_idea.pojo.Result;
import com.example.dms_idea.pojo.Student;
import com.example.dms_idea.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/getStudentList")
    public Result<PageBean<Student>> getStudentList(@RequestBody Map<String ,Object> map){
        int pageNum = (int) map.get("pageNum");
        int pageSize = (int) map.get("pageSize");
        String prop = (String) map.get("prop");
        String order = (String) map.get("order");
        if (prop != null && order != null) {
            if (order.equals("ascending")) order = "asc";
            else if (order.equals("descending")) order = "desc";
            if (prop.equals("studyId")) prop = "study_id";
            else if (prop.equals("buildingName")) prop = "building_name";
            else if (prop.equals("dormitoryName")) prop = "dormitory_name";
            else if (prop.equals("entranceYear")) prop = "entrance_year";
            else if (prop.equals("insName")) prop = "ins_name";
            else if (prop.equals("majorName")) prop = "major_name";
            else if (prop.equals("clazzName")) prop = "clazz_name";
        }
        PageBean<Student> pageBean = studentService.getStudentList(pageNum, pageSize, prop, order,map);
        return Result.success(pageBean);
    }

    @GetMapping("/getStudentById")
    public Result<Student> getStudentById(Integer id){
        Student student = studentService.getStudentById(id);
        return Result.success(student);
    }
}
