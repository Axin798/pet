package cn.jingchenxi.gateway.exception;

import cn.jingchenxi.gateway.result.Result;
import cn.jingchenxi.gateway.result.ResultCode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author : zhangxin
 * @since : 2023/1/14
 **/
@RestControllerAdvice
public class GlobalException {
    //private static final Logger logger = LoggerFactory.getLogger(GlobalException.class);

    /**
     * 处理空指针的异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, NullPointerException e) {
        //logger.error("发生空指针异常！原因是:", e);
        return Result.result(ResultCode.SERVER_ERROR, e.getMessage(), null);
    }


    /**
     * 处理其他异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(Exception e) {
        //logger.error("未知异常！原因是:", e);
        return Result.result(ResultCode.SERVER_ERROR, e.getMessage(), null);
    }
}
