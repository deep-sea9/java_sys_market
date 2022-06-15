package com.gdupt.hai.dao;

import com.gdupt.hai.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    //查询用户   使用多个参数时，需要加上@Param注解，否则会报错
    User selectUser(@Param("username")String username,@Param("password")String password);

    //获取用户列表
    List<User> getUserList();

    //修改密码
    int updatePwd(@Param("username") String username,@Param("password")String password);
}
