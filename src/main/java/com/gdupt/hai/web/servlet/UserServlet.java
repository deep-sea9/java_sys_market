package com.gdupt.hai.web.servlet;

import com.alibaba.fastjson.JSONObject;
import com.gdupt.hai.domain.User;
import com.gdupt.hai.service.UserService;
import com.gdupt.hai.service.impl.UserServiceImpl;
import com.gdupt.hai.web.baseServlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserServlet")
public class UserServlet extends BaseServlet {

    public void getUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService service = new UserServiceImpl();
        List<User> list = service.getUserList();
        JSONObject object = new JSONObject();
        if (list.isEmpty()){
            object.put("status",400);
        }else {
            object.put("status",200);
            object.put("userList",list);
        }
        response.getWriter().print(object);
    }

    public void updatePwd(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserService service = new UserServiceImpl();
        int result = service.updatePwd(username,password);
        JSONObject object = new JSONObject();
        if (result != 0){
            object.put("status",200);
        }else {
            object.put("status",400);
        }
        response.getWriter().print(object);

    }
}
