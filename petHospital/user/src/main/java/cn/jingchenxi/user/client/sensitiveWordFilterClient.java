package cn.jingchenxi.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : jingchenxi
 * @since : 2023/2/3
 **/
@FeignClient("sensitiveWord")
public interface sensitiveWordFilterClient {
    @RequestMapping("/wordFilter")
    String wordFilter(@RequestParam String str);
}
