package com.example.dms_idea.service.imp;

import com.example.dms_idea.dao.InstituteMapper;
import com.example.dms_idea.pojo.Institute;
import com.example.dms_idea.pojo.PageBean;
import com.example.dms_idea.service.InstituteService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InstituteServiceImpl implements InstituteService {

    @Autowired
    private InstituteMapper instituteMapper;

    @Override
    public PageBean<Institute> getInstituteList(int pageNum, int pageSize, String prop, String order, Map<String, Object> map) {
        PageBean<Institute> pageBean = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);

        List<Institute> list = instituteMapper.getManagerList(prop, order,map);

        Page<Institute> pageBeanList = (Page<Institute>) list;

        pageBean.setTotal(pageBeanList.getTotal());
        pageBean.setItems(pageBeanList.getResult());
        return pageBean;
    }

    @Override
    public void addInstitute(String name) {
        instituteMapper.addInstitute(name);
    }

    @Override
    public Institute getInstituteByName(String name) {
        return instituteMapper.getInstituteByName(name);
    }

    @Override
    public Institute getInstituteById(Integer id) {
        return instituteMapper.getInstituteById(id);
    }

    @Override
    public void deleteInstitute(Integer id) {
        instituteMapper.deleteInstitute(id);
    }

    @Override
    public void updateInstitute(Institute institute) {
        instituteMapper.updateInstitute(institute);
    }
}
