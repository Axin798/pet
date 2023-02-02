package cn.jingchenxi.user.service.serviceImpl;

import cn.jingchenxi.user.entity.User;
import cn.jingchenxi.user.mapper.UserMapper;
import cn.jingchenxi.user.service.UserService;
import cn.jingchenxi.user.utils.UserInformationUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jingchenxi
 * @since 2023-02-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User findUserByPhone(String userPhone) {
        return userMapper.findUserByPhone(userPhone);
    }

    @Override
    public Boolean insertUser(JSONObject jsonObject) {
        String userPhone = jsonObject.getString("userPhone");
        String userName = new UserInformationUtil().randomName();
        String userPassword = new UserInformationUtil().randomPassword();
        return userMapper.insertUser(userPhone, userName, userPassword, "用户") == 1;
    }


}
