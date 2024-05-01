package com.example.dms_idea.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairApplication {
    private Integer id;
    private Integer state;
    private String picture;
    private String troubleItem;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    private Integer stuId;
    private String stuName;
    private String stuStudyId;
    private String phone;
    private Integer dorId;
    private String dorName;
    private Integer unitNumber;
    private Integer floorNumber;
    private String buildingName;
    private Integer buildingManId;
    private Integer managerId;
    private String managerName;
    private String managerMessage;
}
