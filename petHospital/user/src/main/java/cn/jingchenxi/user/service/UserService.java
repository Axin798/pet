package cn.jingchenxi.user.service;

import cn.jingchenxi.user.entity.User;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jingchenxi
 * @since 2023-02-02
 */
public interface UserService extends IService<User> {
    /**
     * 根据用户手机号查询用户
     *
     * @param userPhone 用户手机号
     * @return User
     */
    User findUserByPhone(String userPhone);

    Boolean insertUser(JSONObject jsonObject);
}
