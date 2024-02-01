package com.example.dms_idea.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Major {
    private Integer id;
    private String name;
    private Integer clazzNumber;
    private Integer stuNumber;
    private String insName;
}
