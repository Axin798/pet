package cn.jingchenxi.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : jingchenxi
 * @since : 2023/2/2
 **/
@SpringBootApplication
@MapperScan("cn.jingchenxi.user.mapper")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
