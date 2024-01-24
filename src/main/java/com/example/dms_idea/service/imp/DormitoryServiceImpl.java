package com.example.dms_idea.service.imp;

import com.example.dms_idea.dao.DormitoryMapper;
import com.example.dms_idea.service.DormitoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DormitoryServiceImpl implements DormitoryService {

    @Autowired
    private DormitoryMapper dormitoryMapper;

}
