package com.yilan.awesome.exception;

import com.yilan.awesome.base.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * @author： yilan0916
 * @date: 2024/6/27
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(NullPointerException.class)
    public ResponseResult<?> handleNullPointerException(NullPointerException e) {
        log.error("空指针异常", e);
        return ResponseResult.fail("空指针异常");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseResult<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("------->MethodArgumentNotValidException参数异常-------- ", e);
        return ResponseResult.fail("参数异常:" + e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public Object handleConstraintViolationException(ConstraintViolationException e) {
        log.error("------->ConstraintViolationException-------- ", e);

        return ResponseResult.fail("参数异常:", e.getMessage());
    }


    /**
     * 处理其他异常
     *
     * @param req
     * @param e
     * @return
     */    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseResult exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("未知异常！原因是:", e);
        return ResponseResult.fail("未知异常！");
    }

}
