package com.example.dms_idea.exception;

import com.example.dms_idea.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        System.out.println("全局异常工作啦！");
        e.printStackTrace();

        if(e instanceof BindException) {
            BindException ex = (BindException) e;
            List<ObjectError> errors = ex.getAllErrors();
            ObjectError error = errors.get(0);
            String msg = error.getDefaultMessage();
            return Result.error(StringUtils.hasLength(msg) ? msg : "操作失败");
        }
        else {
            return Result.error(StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "操作失败");
        }
    }
}
