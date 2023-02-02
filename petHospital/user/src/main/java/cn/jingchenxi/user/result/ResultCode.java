package cn.jingchenxi.user.result;

/**
 * @author : jingchenxi
 * @since : 2023/2/3
 **/
public class ResultCode {
    public static final int SUCCESS = 200;  // 成功处理请求
    public static final int PASSWORD_ERROR = 400; //密码错误
    public static final int CHECKCODE_ERROR = 401; //验证码错误

    public static final int NOT_USER = 403;   //用户不存在
    public static final int NOT_LOGIN = 402;  // 未登录

    public static final int SERVER_ERROR = 500;   //服务器内部错误
}
