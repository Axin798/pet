package cn.jingchenxi.user.mapper;

import cn.jingchenxi.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author jingchenxi
 * @since 2023-02-02
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据用户手机号查询用户
     *
     * @param userPhone 用户手机号
     * @return User类
     */
    User findUserByPhone(String userPhone);

    Integer insertUser(String userPhone, String userName, String userPassword, String role);

}
