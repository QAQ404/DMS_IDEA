package com.example.dms_idea.controller;

import com.example.dms_idea.pojo.*;
import com.example.dms_idea.service.EmailService;
import com.example.dms_idea.service.ManagerService;
import com.example.dms_idea.service.StudentService;
import com.example.dms_idea.service.UserService;
import com.example.dms_idea.untils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private StudentService studentService;

    @PostMapping("/addEmail")
    public Result addEmail(@RequestBody Map<String ,Object> map){
        Map<String, Object> user = ThreadLocalUtil.get();
        Integer send = (Integer) user.get("id");
        Integer sendRole = userService.getUserRoleById(send);
        String sendId = "";
        if(sendRole == 1){
            Student student = studentService.getSimpleStudentById(send);
            sendId = student.getStudyId();
        }
        else{
            Manager manager = managerService.getManagerById(send);
            sendId = manager.getWorkId();
        }

        List<Integer> reList = (List<Integer>) map.get("reId");
        Integer re = -1;
        if(reList.get(0) == 1){
            re = reList.get(5);
        }else{re = reList.get(1);}
        String reId = "";
        if(reList.get(0) == 1){
            Student student = studentService.getSimpleStudentById(re);
            reId = student.getStudyId();
        }
        else{
            Manager manager = managerService.getManagerById(re);
            reId = manager.getWorkId();
        }

        String theme = (String) map.get("theme");
        String content = (String) map.get("content");

        emailService.addEmail(send,sendId,re,reId,theme,content);

        return Result.success();
    }

    @PostMapping("/getEmailList")
    public Result<PageBean<Email>> getEmailList(@RequestBody Map<String, Object> map){
        int pageNum = (int) map.get("pageNum");
        int pageSize = (int) map.get("pageSize");
        PageBean<Email> pageBean = emailService.getEmailList(pageNum, pageSize, map);
        return Result.success(pageBean);
    }

    @DeleteMapping("/deleteEmail")
    public Result deleteEmail(Integer id,String show){
        Email email = emailService.getEmailById(id);
        if(email.getSendShow() == 1 && email.getReShow() == 1){
            if(show.equals("收件箱")){
                emailService.updateReShow(id);
            }else{
                emailService.updateSendShow(id);
            }
        }
        else {
            emailService.deleteEmail(id);
        }
        return Result.success();
    }
}
