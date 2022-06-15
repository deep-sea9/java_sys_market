package com.gdupt.hai.service;

import com.gdupt.hai.domain.User;

import java.util.List;


public interface LoginService {

    User selectUser(String username,String password);


}
