package com.wenyi.wenyi.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.wenyi.wenyi.entity.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SystemExceptionHandler {
    @ExceptionHandler(TokenExpiredException.class)
    public Result paramsExceptionHandler(HttpServletRequest request, Exception e){
        String errorMsg = "认证过期";
        int errorCode = 403;
        return Result.fail(errorCode,errorMsg);
    }
}
