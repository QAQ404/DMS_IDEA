package com.example.dms_idea.controller;

import com.example.dms_idea.pojo.*;
import com.example.dms_idea.service.BuildingService;
import com.example.dms_idea.service.ChangeDormitoryApplicationService;
import com.example.dms_idea.service.DormitoryService;
import com.example.dms_idea.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/changeDormitoryApplication")
public class ChangeDormitoryApplicationController {

    @Autowired
    private ChangeDormitoryApplicationService changeDorAppService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private DormitoryService dormitoryService;

    @Autowired
    private BuildingService buildingService;

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
        Student oldStu = studentService.getSimpleStudentById(stuId);
        if(Bed.get(4) == -1){
            changeDorAppService.addApplication(stuId,oldStu.getDormitoryId(),Bed.get(3),-1,null,null);
        }
        else {
            Student student = studentService.getSimpleStudentById(Bed.get(4));

            changeDorAppService.addApplication(stuId,oldStu.getDormitoryId(),student.getDormitoryId(),student.getId(),student.getName(),student.getStudyId());
        }
        return Result.success();
    }

    @DeleteMapping("/deleteApplication")
    public Result deleteApplication(Integer id){
        changeDorAppService.deleteApplication(id);
        return Result.success();
    }

    @PutMapping("/refusesApplication")
    public Result refusesApplication(Integer id){
        changeDorAppService.updateStateById(id,1);
        return Result.success();
    }

    @PutMapping("/acceptApplication")
    public Result acceptApplication(Integer id){
        changeDorAppService.updateStateById(id,2);
        ChangeDormitoryApplication changeDorApp = changeDorAppService.getApplicationById(id);
        if(changeDorApp.getNewStuId()==-1){
            studentService.updateStudentDormitoryId(changeDorApp.getStuId(),changeDorApp.getNewId());
            dormitoryService.addStudentNumber(changeDorApp.getOldId(),-1);
            dormitoryService.addStudentNumber(changeDorApp.getNewId(),1);
            Dormitory newDor = dormitoryService.getDormitoryById(changeDorApp.getNewId());
            if(changeDorApp.getOldBuildingName() != changeDorApp.getNewBuildingName()){
                Dormitory oldDor = dormitoryService.getDormitoryById(changeDorApp.getOldId());
                buildingService.addStudentNumber(oldDor.getBuildingId(),-1);
                buildingService.addStudentNumber(newDor.getBuildingId(),1);
            }
            if(newDor.getBedNumber()-newDor.getStuNumber()==0){
                //与该寝室空闲床位有关的申请全部失效
                changeDorAppService.updateStateByNewIdAndNewStuId(changeDorApp.getNewId(),-1,-1);
            }
            //与申请人有关的申请全部失效
            changeDorAppService.updateStateByNewStuId(changeDorApp.getStuId(),-1);
        }
        else{
            studentService.updateStudentDormitoryId(changeDorApp.getStuId(),changeDorApp.getNewId());
            studentService.updateStudentDormitoryId(changeDorApp.getNewStuId(),changeDorApp.getOldId());
            //与目标人物有关的申请全部失效
            changeDorAppService.updateStateByNewStuId(changeDorApp.getNewStuId(),-1);
            changeDorAppService.updateStateByStuId(changeDorApp.getNewStuId(),-1);
            //与申请人有关的申请全部失效
            changeDorAppService.updateStateByNewStuId(changeDorApp.getStuId(),-1);
        }
        return Result.success();
    }
}
