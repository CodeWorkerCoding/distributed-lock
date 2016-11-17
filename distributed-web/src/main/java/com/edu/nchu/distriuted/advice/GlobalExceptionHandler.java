package com.edu.nchu.distriuted.advice;

import com.edu.nchu.distributed.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.sql.SQLException;

/**
 * 全局异常处理
 * Created by fujianjian on 2016/11/17.
 */
@EnableWebMvc
@ControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final static  String MSG_KEY = "msg";

    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleBizExp(ModelMap modelMap, Exception e){
        LOGGER.info("Business exception handler", e);
        /*request.getSession(true) //如果找不到session 则创建一个
                .setAttribute(MSG_KEY, e.getMessage());*/
        modelMap.put(MSG_KEY, e.getMessage());
        return "/exception/biz_error";
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleSqlException(ModelMap modelMap, Exception e){
        LOGGER.info("SQL Exception:", e);
        modelMap.put(MSG_KEY, e.getMessage());
        return "/exception/sql_error";
    }
}
