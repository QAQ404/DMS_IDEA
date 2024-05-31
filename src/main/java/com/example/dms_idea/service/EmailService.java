package com.example.dms_idea.service;

import com.example.dms_idea.pojo.Email;
import com.example.dms_idea.pojo.PageBean;

import java.util.Map;

public interface EmailService {
    void addEmail(Integer send, String sendId, Integer re, String reId, String theme, String content);
    PageBean<Email> getEmailList(int pageNum, int pageSize, Map<String, Object> map);
    Email getEmailById(Integer id);
    void updateReShow(Integer id);
    void updateSendShow(Integer id);
    void deleteEmail(Integer id);
}
