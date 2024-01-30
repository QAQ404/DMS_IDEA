package com.example.dms_idea.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Institute {
    private Integer id;
    private String name;
    private Integer majorNumber;
    private Integer stuNumber;
    private Integer clazzNumber;
}
