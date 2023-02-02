package cn.jingchenxi.user.controller;

import cn.jingchenxi.user.entity.User;
import cn.jingchenxi.user.result.Result;
import cn.jingchenxi.user.result.ResultCode;
import cn.jingchenxi.user.service.UserService;
import cn.jingchenxi.user.utils.JwtTokenUtil;
import cn.jingchenxi.user.utils.RedisUtil;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jingchenxi
 * @since 2023-02-02
 */
@RestController
@RequestMapping("/user")
public class UserController {
    final UserService userService;
    final RedisUtil redis;

    public UserController(UserService userService, RedisUtil redis) {
        this.userService = userService;
        this.redis = redis;
    }

    /**
     * 用户登陆接口
     *
     * @param response 用于携带token返回
     * @param json     请求的json格式数据
     * @return Result类
     */
    @RequestMapping("/loginByPassword")
    public Result loginByPassword(HttpServletResponse response, @RequestBody JSONObject json) {
        User user = userService.findUserByPhone(json.getString("userPhone"));
        if (user == null) {
            return Result.result(ResultCode.NOT_USER, "用户不存在", null);
        } else if (!json.getString("userPassword").equals(user.getUserPassword())) {
            return Result.result(ResultCode.PASSWORD_ERROR, "密码错误", null);
        } else {
            String token = JwtTokenUtil.sign(user.getUserId(), json.getBoolean("isRememberMe"), null);
            response.setHeader(JwtTokenUtil.TOKEN_HEADER, JwtTokenUtil.TOKEN_PREFIX + token);
            return Result.result(ResultCode.SUCCESS, "登陆成功", null);
        }
    }

    @RequestMapping("/loginOrRegister")
    public Result loginOrRegister(HttpServletResponse response, @RequestBody JSONObject jsonObject) {
        String userPhone = jsonObject.getString("userPhone");
        String checkCode = jsonObject.getString("checkCode");
        if (!checkCode.equals(redis.getCacheObject("checkCode-" + userPhone))) {
            return Result.result(ResultCode.CHECKCODE_ERROR, "验证码错误", null);
        } else {
            User user = userService.findUserByPhone(jsonObject.getString("userPhone"));
            if (user == null) {
                if (userService.insertUser(jsonObject)) {
                    return Result.result(ResultCode.SUCCESS, "注册成功", null);
                } else {
                    return Result.result(ResultCode.SERVER_ERROR, "服务器内部错误", null);
                }
            } else {
                String token = JwtTokenUtil.sign(user.getUserId(), jsonObject.getBoolean("isRememberMe"), null);
                response.setHeader(JwtTokenUtil.TOKEN_HEADER, JwtTokenUtil.TOKEN_PREFIX + token);
                return Result.result(ResultCode.SUCCESS, "登陆成功", null);
            }
        }
    }


}
