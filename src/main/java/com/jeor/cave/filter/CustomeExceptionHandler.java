package com.jeor.cave.filter;

import com.jeor.cave.common.Result;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.util.stream.Collectors;

/**
 * @Author: Jiangguoda
 * @Date: 2020/8/21 14:23
 */
@RestControllerAdvice
public class CustomeExceptionHandler {

    @ExceptionHandler({ValidationException.class, MethodArgumentNotValidException.class})
    public Result<?> validationErrorHandle(Exception e){
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex  = (MethodArgumentNotValidException) e;
            String errorInformation = ex.getBindingResult().getAllErrors()
                    .stream().map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(","));
            return Result.error(400, errorInformation);
        }
        return Result.error(400, e.getMessage());
    }
}
