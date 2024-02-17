package com.example.dms_idea.controller;

import com.example.dms_idea.pojo.PageBean;
import com.example.dms_idea.pojo.Result;
import com.example.dms_idea.pojo.Student;
import com.example.dms_idea.pojo.User;
import com.example.dms_idea.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private DormitoryService dormitoryService;

    @Autowired
    private InstituteService instituteService;

    @Autowired
    private MajorService majorService;

    @Autowired
    private ClazzService clazzService;

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

    @PostMapping("/addStudent")
    public Result addStudent(@RequestBody List<Map<String,Object>> mapList){
        for(Map<String,Object> map : mapList){
            User user = userService.getUserByUsername((String) map.get("studyId"));
            if(user != null) return Result.error((String) map.get("studyId")+":学号已存在");
        }
        for(Map<String,Object> map : mapList){
            String name = (String) map.get("name");
            String gender = (String) map.get("gender");
            String entranceYear = (String) map.get("entranceYear");
            String  studyId= (String) map.get("studyId");
            String  password= (String) map.get("password");
            List<Integer> clazzList = (List<Integer>) map.get("clazzId");
            System.out.println(name+gender+entranceYear+studyId+password+clazzList);
            userService.addUser(studyId,password,name,1);
            User user = userService.getUserByUsername(studyId);
            studentService.addStudent(user.getId(),1,studyId,gender,clazzList.get(0),clazzList.get(1),clazzList.get(2),entranceYear);
            Student student = studentService.getSimpleStudentById(user.getId());
            studentService.addStudentInfo(student.getId());
            buildingService.addStudentNumber(14,1);
            dormitoryService.addStudentNumber(1,1);
            instituteService.addStudentNumber(clazzList.get(0),1);
            majorService.addStudentNumber(clazzList.get(1),1);
            clazzService.addStudentNumber(clazzList.get(2),1);
        }
        return Result.success();
    }
}
