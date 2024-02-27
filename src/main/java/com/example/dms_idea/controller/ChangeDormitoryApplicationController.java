package com.example.dms_idea.controller;

import com.example.dms_idea.pojo.ChangeDormitoryApplication;
import com.example.dms_idea.pojo.PageBean;
import com.example.dms_idea.pojo.Result;
import com.example.dms_idea.pojo.Student;
import com.example.dms_idea.service.ChangeDormitoryApplicationService;
import com.example.dms_idea.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/changeDormitoryApplication")
public class ChangeDormitoryApplicationController {

    @Autowired
    private ChangeDormitoryApplicationService changeDorAppService;

    @Autowired
    private StudentService studentService;

    @PostMapping("/getApplicationList")
    public Result<PageBean<ChangeDormitoryApplication>> getApplicationList(@RequestBody Map<String, Object> map){
        int pageNum = (int) map.get("pageNum");
        int pageSize = (int) map.get("pageSize");
        String prop = (String) map.get("prop");
        String order = (String) map.get("order");
        if (prop != null && order != null) {
            if (order.equals("ascending")) order = "asc";
            else if (order.equals("descending")) order = "desc";
            if (prop.equals("createTime")) prop = "create_time";
            else if (prop.equals("updateTime")) prop = "update_time";
        }
        PageBean<ChangeDormitoryApplication> pageBean = changeDorAppService.getApplicationList(pageNum, pageSize, prop, order, map);
        return Result.success(pageBean);
    }

    @PostMapping("/addApplication") //添加申请
    public Result addApplication(@RequestBody Map<String,Object> map){
        Integer stuId = (Integer) map.get("Data");
        List<Integer> Bed = (List<Integer>) map.get("Bed");
        if(Bed.get(4) == -1){
            changeDorAppService.addApplication(stuId,Bed.get(3),-1,null,null);
        }
        else {
            Student student = studentService.getSimpleStudentById(Bed.get(4));
            changeDorAppService.addApplication(stuId,student.getDormitoryId(),student.getId(),student.getName(),student.getStudyId());
        }
        return Result.success();
    }
}
