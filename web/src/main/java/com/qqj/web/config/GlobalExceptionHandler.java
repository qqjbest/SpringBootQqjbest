package com.qqj.web.config;
/**
 * @auther qqjbest qqjbest
 * @create 2019-05-05
 */

import com.qqj.bean.BusinessException;
import com.qqj.bean.Result;
import com.qqj.bean.ResultCodeEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.qqj.bean.Result.ERR_2000_BUSINESS_ERR;

/**
 * 全局异常处理配置
 * @auther qjqiu
 * @create 2019-05-05
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result<String> BusinessExceptionHandle(BusinessException exception) throws Exception {
        return handleErrorInfo(exception.getMessage(), exception);
    }


    private Result<String> handleErrorInfo(String message, Exception exception) {
        Result<String> errResult = new Result<>();
        errResult.setStatus(ResultCodeEnum.FAIL);
        errResult.setErrCode(ERR_2000_BUSINESS_ERR);
        errResult.setMessage(message);
        return errResult;
    }

}
