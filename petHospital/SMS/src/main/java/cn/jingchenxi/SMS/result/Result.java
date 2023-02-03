package cn.jingchenxi.SMS.result;

import lombok.Data;

/**
 * @author : jingchenxi
 * @since : 2023/2/2
 **/
@Data
public class Result {
    private int code;   //返回状态码
    private String msg; //返回的信息
    private Object data;     //返回的数据

    public static Result result(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
}
