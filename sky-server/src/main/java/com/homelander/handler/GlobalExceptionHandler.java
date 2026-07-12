package com.homelander.handler;

import com.homelander.constant.MessageConstant;
import com.homelander.exception.BaseException;
import com.homelander.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * ClassName:GlobalExceptionHandler
 * Package:com.homelander.handler
 * Description:全局异常处理器，处理项目中抛出的业务异常
 *
 * @Author Heisenberg
 * @Create 2026/7/11 17:00
 * @Version 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**处理 SQL 唯一约束异常
     *从异常信息中提取重复值
     *返回友好的提示信息（如 "zhangsan 已存在"）
     * 其他 SQL 异常返回 "未知错误"
     * @param exception
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException exception){
        // Duplicate entry 'zhangsan' for key 'employee.idx_username
        String message = exception.getMessage();
        if (message.contains("Duplicate entry")){
            // 给前端一个提示信息
            String[] split = message.split(" ");
            String username = split[2];
            String msg = username + MessageConstant.ALREADY_EXISTS;
            return Result.error(msg);
        }else {
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }
    }
}
