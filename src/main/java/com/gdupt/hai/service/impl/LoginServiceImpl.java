package com.gdupt.hai.service.impl;

import com.gdupt.hai.dao.UserDao;
import com.gdupt.hai.domain.User;
import com.gdupt.hai.service.LoginService;
import com.gdupt.hai.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

public class LoginServiceImpl implements LoginService {

    SqlSession sqlSession = MyBatisUtils.getSqlSession();
    UserDao dao = sqlSession.getMapper(UserDao.class);

    @Override
    public User selectUser(String username, String password) {
        User user = dao.selectUser(username,password);
        sqlSession.close();
        return user;
    }

}
