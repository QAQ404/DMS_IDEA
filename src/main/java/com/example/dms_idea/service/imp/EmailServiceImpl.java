package com.example.dms_idea.service.imp;

import com.example.dms_idea.dao.EmailMapper;
import com.example.dms_idea.pojo.ChangeDormitoryApplication;
import com.example.dms_idea.pojo.Email;
import com.example.dms_idea.pojo.PageBean;
import com.example.dms_idea.service.EmailService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailMapper emailMapper;

    @Override
    public void addEmail(Integer send, String sendId, Integer re, String reId, String theme, String content) {
        emailMapper.addEmail(send,sendId,re,reId,theme,content);
    }

    @Override
    public PageBean<Email> getEmailList(int pageNum, int pageSize, Map<String, Object> map) {
        PageBean<Email> pageBean = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);
        Integer show = 0;
        String mapShow = (String)map.get("show");
        if(mapShow.equals("收件箱")) show = 1;
        else show = 2;
        List<Email> list = emailMapper.getEmailList(map,show);

        Page<Email> pageBeanList = (Page<Email>) list;

        pageBean.setTotal(pageBeanList.getTotal());
        pageBean.setItems(pageBeanList.getResult());
        return pageBean;
    }

    @Override
    public Email getEmailById(Integer id) {
        return emailMapper.getEmailById(id);
    }

    @Override
    public void updateReShow(Integer id) {
        emailMapper.updateReShow(id);
    }

    @Override
    public void updateSendShow(Integer id) {
        emailMapper.updateSendShow(id);
    }

    @Override
    public void deleteEmail(Integer id) {
        emailMapper.deleteEmail(id);
    }
}
