package com.example.dms_idea.controller;

import com.example.dms_idea.pojo.Result;
import com.example.dms_idea.untils.AliOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping
public class FileUploadController {


    @PostMapping("/upload")//文件大小要小于1M
    public Result<String> upload(MultipartFile file) throws Exception {
        if (file.getSize() > 1048576) {
            return Result.success("big");
        }
        String originalFilename = file.getOriginalFilename();
        String filename = UUID.randomUUID().toString() +
                originalFilename.substring(originalFilename.lastIndexOf("."));

        String url = AliOssUtil.uploadFile(filename, file.getInputStream());
        return Result.success(url);
    }
}
