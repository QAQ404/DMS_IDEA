package com.example.dms_idea.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    private Integer id;
    private Integer send;
    private String sendId;
    private String sendName;
    private Integer sendShow;
    private Integer re;
    private String reId;
    private String reName;
    private Integer reShow;
    private String theme;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
