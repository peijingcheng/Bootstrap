package org.jiangbeicheng.vhr01.exception;

import org.jiangbeicheng.vhr01.bean.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @ClassName GlobalExceptionHandler
 * @Description TODO
 * @Author 安生と犬
 * @Date 2020/6/15 18:56
 * @Mail anshenanddong@gmail.com
 * @Version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SQLException.class)
    public RespBean sqlException(SQLException e){
        if(e instanceof SQLIntegrityConstraintViolationException){
            return RespBean.error("该数据有关联数据，操作失败！");
        }
        return RespBean.error("数据库异常，操作失败！");
    }

}
