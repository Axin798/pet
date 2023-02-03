package cn.jingchenxi.SMS.controller;

import cn.jingchenxi.SMS.result.Result;
import cn.jingchenxi.SMS.result.ResultCode;
import cn.jingchenxi.SMS.utils.RedisUtil;
import cn.jingchenxi.SMS.utils.SMSUtil;
import cn.jingchenxi.SMS.utils.ValidateCodeUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author : jingchenxi
 * @since : 2023/2/3
 **/
@RestController
@RequestMapping("/sms")
public class SMSController {
    final RedisUtil redisUtil;

    public SMSController(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @RequestMapping("/sendSMS")
    public Result sendSMS(@RequestParam("userPhone") String userPhone) {
        String code = String.valueOf(ValidateCodeUtil.generateValidateCode(4));
        try {
            SMSUtil.sendSMS(userPhone, code);
            redisUtil.setCacheObject("code" + userPhone, code, 5, TimeUnit.MINUTES);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Result.result(ResultCode.SUCCESS, "验证码已发送", null);
    }
}
