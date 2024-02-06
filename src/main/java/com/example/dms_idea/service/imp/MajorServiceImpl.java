package com.example.dms_idea.service.imp;

import com.example.dms_idea.dao.MajorMapper;
import com.example.dms_idea.pojo.Major;
import com.example.dms_idea.pojo.PageBean;
import com.example.dms_idea.service.MajorService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MajorServiceImpl implements MajorService {

    @Autowired
    private MajorMapper majorMapper;

    @Override
    public PageBean<Major> getMajorList(int pageNum, int pageSize, String prop, String order, Map<String, Object> map) {
        PageBean<Major> pageBean = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);

        List<Major> list = majorMapper.getMajorList(prop, order,map);

        Page<Major> pageBeanList = (Page<Major>) list;

        pageBean.setTotal(pageBeanList.getTotal());
        pageBean.setItems(pageBeanList.getResult());
        return pageBean;
    }

    @Override
    public Major getMajorByNameInsName(String name, String  insName) {
        return majorMapper.getMajorByNameInsName(name,insName);
    }

    @Override
    public void addMajor(String name, Integer insId) {
        majorMapper.addMajor(name,insId);
    }

    @Override
    public Major getMajorById(Integer id) {
        return majorMapper.getMajorById(id);
    }

    @Override
    public void updateMajor(Major major) {
        majorMapper.updateMajor(major);
    }

    @Override
    public void deleteMajor(Integer id) {
        majorMapper.deleteMajor(id);
    }

    @Override
    public List<Major> getMajorNameList(String insName) {
        return majorMapper.getMajorNameList(insName);
    }

    @Override
    public void addClazzNumber(Integer id, int i) {
        majorMapper.addClazzNumber(id,i);
    }
}
