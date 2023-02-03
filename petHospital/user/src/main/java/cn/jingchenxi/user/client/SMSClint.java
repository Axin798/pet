package cn.jingchenxi.user.client;

import cn.jingchenxi.user.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : jingchenxi
 * @since : 2023/2/3
 **/
@FeignClient("SMS")
public interface SMSClint {
    @RequestMapping("/sms/sendSMS")
    Result sendSMS(@RequestParam String userPhone);
}
