package com.example.dms_idea.dao;

import com.example.dms_idea.pojo.Email;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmailMapper {

    void addEmail(Integer send, String sendId, Integer re, String reId, String theme, String content);

    List<Email> getEmailList(Map<String, Object> map,Integer show);

    Email getEmailById(Integer id);

    void updateReShow(Integer id);

    void updateSendShow(Integer id);

    void deleteEmail(Integer id);
}
