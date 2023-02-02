package cn.jingchenxi.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author jingchenxi
 * @since 2023-02-02
 */
@Getter
@Setter
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @TableField("user_phone")
    private String userPhone;

    @TableField("user_password")
    private String userPassword;

    @TableField("user_name")
    private String userName;

    @TableField("pet_id")
    private Integer petId;

    @TableField("role_id")
    private Integer roleId;
}
