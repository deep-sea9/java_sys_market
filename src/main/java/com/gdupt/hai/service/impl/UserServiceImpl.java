package com.gdupt.hai.service.impl;

import com.gdupt.hai.dao.UserDao;
import com.gdupt.hai.domain.User;
import com.gdupt.hai.service.UserService;
import com.gdupt.hai.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserServiceImpl implements UserService {
    SqlSession sqlSession = null;
    UserDao dao = null;

    @Override
    public List<User> getUserList() {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(UserDao.class);
        List<User> userList = dao.getUserList();
        sqlSession.close();
        return userList;
    }

    @Override
    public int updatePwd(String username, String password) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(UserDao.class);
        int result = dao.updatePwd(username,password);
        sqlSession.commit();
        sqlSession.close();
        return result;
    }
}
