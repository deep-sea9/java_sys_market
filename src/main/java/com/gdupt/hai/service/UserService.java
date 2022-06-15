package com.gdupt.hai.service;

import com.gdupt.hai.domain.User;

import java.util.List;

public interface UserService {
    List<User> getUserList();

    int updatePwd(String username,String password);

}
