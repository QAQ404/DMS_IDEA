package com.example.dms_idea.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeDormitoryApplication {
    private Integer id;
    private Integer state;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    private Integer stuId;
    private String stuName;
    private String stuStudyId;
    private Integer oldId; //原寝室的Id
    private String oldName;//原寝室的名称
    private Integer oldUnitNumber;
    private Integer oldFloorNumber;
    private String oldBuildingName;
    private Integer newId;//新寝室的Id
    private String newName;//新寝室的名称
    private Integer newUnitNumber;
    private Integer newFloorNumber;
    private String newBuildingName;
    private Integer newStuId;
    private String newStuName;
    private String newStuStudyId;
}
