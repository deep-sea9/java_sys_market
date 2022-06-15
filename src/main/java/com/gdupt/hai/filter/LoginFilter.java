package com.gdupt.hai.filter;


import com.alibaba.fastjson.JSONObject;
import com.gdupt.hai.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录验证的过滤器
 */

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // System.out.println(req);
        //0.强制转换
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //1.获取资源请求路径
        /*String url = request.getRequestURI();
        System.out.println("url是：" + url);
        if (url.contains("/login")) {
            //chain.doFilter(req, resp);
        }
        else {
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("user");
            System.out.println("----"+user+"=======");
            if (user!= null){
                //chain.doFilter(req, resp);
            }
            else {
                 //JSONObject jsonObject = new JSONObject();
                 //jsonObject.put("msg","账号还没登陆，请先登陆");
                 response.getWriter().print("账号还没登陆，请先登陆");
            }
        }*/

    }

}

