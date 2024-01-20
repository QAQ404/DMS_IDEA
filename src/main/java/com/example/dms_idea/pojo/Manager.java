package com.example.dms_idea.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manager {
    private int id;
    private String name;
    private String gender;
    private String phone;
    private String picture;
    private String email;
    private int buildingNumber;
    private String workId;
}
