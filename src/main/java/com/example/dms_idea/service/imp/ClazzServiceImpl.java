package com.example.dms_idea.service.imp;

import com.example.dms_idea.dao.ClazzMapper;
import com.example.dms_idea.pojo.Clazz;
import com.example.dms_idea.pojo.PageBean;
import com.example.dms_idea.service.ClazzService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public PageBean<Clazz> getClazzList(int pageNum, int pageSize, String prop, String order, Map<String, Object> map) {
        PageBean<Clazz> pageBean = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);

        List<Clazz> list = clazzMapper.getClazzList(prop, order,map);

        Page<Clazz> pageBeanList = (Page<Clazz>) list;

        pageBean.setTotal(pageBeanList.getTotal());
        pageBean.setItems(pageBeanList.getResult());
        return pageBean;
    }

    @Override
    public Clazz getClazzByName(Integer name, String insName, String majorName, Integer entranceYear) {
        return clazzMapper.getClazzByName(name,insName,majorName,entranceYear);
    }

    @Override
    public void addClazz(Integer name, Integer majorId, Integer entranceYear) {
        clazzMapper.addClazz(name,majorId,entranceYear);
    }

    @Override
    public Clazz getClazzById(Integer id) {
        return clazzMapper.getClazzById(id);
    }

    @Override
    public void updateClazz(Clazz clazz) {
        clazzMapper.updateClazz(clazz);
    }

    @Override
    public void deleteClazz(Integer id) {
        clazzMapper.deleteClazz(id);
    }

    @Override
    public List<Clazz> getClazzList() {
        Map<String,Object> map = new HashMap<>();
        return clazzMapper.getClazzList(null,null,map);
    }

    @Override
    public List<Clazz> getClazzListByMajorInstituteYear(String majorName, String insName,Integer year) {

        return clazzMapper.getClazzListByMajorInstituteYear(majorName,insName,year);
    }

    @Override
    public void addStudentNumber(Integer id, int num) {
        clazzMapper.addStudentNumber(id,num);
    }

    @Override
    public List<Integer> getClazzYearByMajorInstitute(String majorName, String insName) {
        return clazzMapper.getClazzYearByMajorInstitute(majorName,insName);
    }
}
