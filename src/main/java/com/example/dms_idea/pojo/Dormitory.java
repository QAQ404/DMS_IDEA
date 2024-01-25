package com.example.dms_idea.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dormitory {
    private int id;
    private String name;
    private int unitNumber;
    private int stuNumber;
    private int bedNumber;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    private int floorNumber;
    private String picture;
    private String buildingId;
    private String buildingName;
    private String manName;
    private String  workId;
}
