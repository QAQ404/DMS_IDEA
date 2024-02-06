package com.example.dms_idea.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clazz {
    private Integer id;
    private Integer name;
    private Integer stuNumber;
    private String majorName;
    private String insName;
    private Integer entranceYear;
}
