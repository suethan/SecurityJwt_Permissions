package com.ethan.auth.comm.exception;

import com.ethan.auth.comm.constant.HttpStatus;
import com.ethan.auth.comm.vo.ResultVo;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
 * @Author: ethan.liu
 * @Date: 2020/4/3 10:10
 */
@ControllerAdvice
public class ComExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultVo Handler(Exception e){
        if (e instanceof ComException){
            ComException tmp = (ComException) e;
            return ResultVo.error(tmp.getCode(),tmp.getMsg());
        }else{
            //登录认证失败
            if (e instanceof AuthenticationException){
                return ResultVo.error(HttpStatus.LOGIN_ERROR_TOKEN_NULL,HttpStatus.LOGIN_ERROR_TOKEN_NULL_MSG);
            }

            //权限不够拒绝访问
            if (e instanceof AccessDeniedException){
                return ResultVo.error(HttpStatus.AUTH_ERROR,HttpStatus.AUTH_ERROR_MSG);
            }


            return ResultVo.error(HttpStatus.AUTH_ERROR,"全局异常，未知错误:"+e.getMessage());
        }
    }
}
