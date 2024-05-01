package com.example.dms_idea.controller;

import com.example.dms_idea.pojo.*;
import com.example.dms_idea.service.*;
import com.example.dms_idea.untils.JwtUtil;
import com.example.dms_idea.untils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private DormitoryService dormitoryService;

    @Autowired
    private BuildingService buildingService;

    @PostMapping("/login")  //登录
    public Result login(String username, String password) {
        if (username.length() == 0) return Result.error("账号不能为空");
        else if (password.length() == 0) return Result.error("密码不能为空");

        if (username.length() > 20) return Result.error("账号长度需小于20位");
        else if (password.length() > 32) return Result.error("密码长度需小于32位");

        User user = userService.getUser(username, password);
        if (user == null) return Result.error("账号不存在或密码错误");

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("role", user.getRole());
        claims.put("name", user.getName());
        String token = JwtUtil.genToken(claims);

        //把token存储到redis中
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set(token, token, 12, TimeUnit.HOURS);
        return Result.success(token);
    }

    @GetMapping("/userInfo") //获取账号的基本信息
    public Result<Map<String, Object>> getUser() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Map<String, Object> user = new HashMap<>();
        Integer id = (Integer) map.get("id");
        Integer role = (Integer) map.get("role");
        String name = (String) map.get("name");
        user.put("id",id);
        user.put("role",role);
        user.put("name",name);
        if(role == 1){
            Student student = studentService.getStudentById(id);
            user.put("picture",student.getStudentInfo().getPicture());
        }
        else {
            Manager manager = managerService.getManagerById(id);
            user.put("picture",manager.getPicture());
        }
        return Result.success(user);
    }

    @GetMapping("/exit") //退出当前账号
    public Result exit(@RequestHeader("Authorization") String token) {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);
        ThreadLocalUtil.remove();
        return Result.success("退出成功");
    }

    @GetMapping("/getUserList")
    public Result<List<Map<String,Object>>> getUserList(){
        List<Map<String,Object>> userList = new ArrayList<>();

        Map<String,Object> manager = new HashMap<>();
        manager.put("label","宿管");
        manager.put("value",2);
        List<Map<String, Object>> manlist = managerService.getAllManagerName();
        List<Map<String, Object>> managerList = new ArrayList<>();
        for (Map item : manlist) {
            String name = (String) item.get("name");
            int id = (int) item.get("id");
            String workId = (String) item.get("workId");
            Map<String, Object> res = new HashMap<>();
            res.put("label", "编号" + workId  + name);
            res.put("value", id);
            managerList.add(res);
        }
        manager.put("children",managerList);
        userList.add(manager);

        Map<String,Object> stu = new HashMap<>();
        stu.put("label","学生");
        stu.put("value",1);
        List<Map<String, Object>> map = new ArrayList<>();
        List<Building> buildingList = buildingService.getBuildingList();
        for (Building building : buildingList) {
            if (building.getStuNumber() == 0) continue;
            Map<String, Object> buil = new HashMap<>();
            buil.put("label", building.getName());
            buil.put("value", building.getId());
            List<Map<String, Object>> buildingMap = new ArrayList<>();
            List<Integer> unitList = dormitoryService.getDormitoryUnitHasStu(building.getId());
            for (Integer unit : unitList) {
                Map<String, Object> un = new HashMap<>();
                un.put("label", unit + "单元");
                un.put("value", unit);
                List<Map<String, Object>> unitMap = new ArrayList<>();
                List<Integer> floorList = dormitoryService.getDormitoryFlootHasStu(building.getId(), unit);
                for (Integer floor : floorList) {
                    Map<String, Object> fl = new HashMap<>();
                    fl.put("label", floor + "楼");
                    fl.put("value", floor);
                    List<Map<String, Object>> floorMap = new ArrayList<>();
                    List<Dormitory> dormitoryList = dormitoryService.getDormitoryListByBuildingIdUnitFloorHasStu(building.getId(), unit, floor);
                    for (Dormitory dormitory : dormitoryList) {
                        Map<String, Object> dor = new HashMap<>();
                        dor.put("label", dormitory.getName());
                        dor.put("value", dormitory.getId());
                        List<Map<String, Object>> stuMap = new ArrayList<>();
                        List<Student> studentList = studentService.getStudentByDormitoryId(dormitory.getId());
                        for(Student student : studentList){
                            Map<String, Object> st = new HashMap<>();
                            st.put("label",student.getStudyId()+student.getName());
                            st.put("value", student.getId());
                            stuMap.add(st);
                        }
                        dor.put("children", stuMap);
                        floorMap.add(dor);
                    }
                    fl.put("children", floorMap);
                    unitMap.add(fl);
                }
                un.put("children", unitMap);
                buildingMap.add(un);
            }
            buil.put("children", buildingMap);
            map.add(buil);
        }
        stu.put("children",map);

        userList.add(stu);
        return Result.success(userList);
    }
}
