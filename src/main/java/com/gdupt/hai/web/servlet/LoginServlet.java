package com.gdupt.hai.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gdupt.hai.domain.User;
import com.gdupt.hai.service.LoginService;
import com.gdupt.hai.service.impl.LoginServiceImpl;
import com.gdupt.hai.utils.TokenUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = "";
        //获取前端传递过来的用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        JSONObject jsonObject = new JSONObject();
        LoginService service = new LoginServiceImpl();
        User user = service.selectUser(username, password);
        System.out.println(user);
        if (user != null) {  //如果对象不为空，即查到数据库中的数据，则登录成功
            jsonObject.put("status", 200);
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(3600);//3600秒，注意服务器端的3600秒，而不是客户端的
            jsonObject.put("session", session);
        } else {  //如果对象为空，则在数据库中查不到信息，则登录失败
            jsonObject.put("status", 400);
        }
        response.getWriter().print(jsonObject);
    }
}
