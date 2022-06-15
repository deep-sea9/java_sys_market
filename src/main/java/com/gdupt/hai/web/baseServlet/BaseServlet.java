package com.gdupt.hai.web.baseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 抽取servlet
 */

@WebServlet(name = "BaseServlet")
public class BaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //获取请求标识
            String methodName = request.getParameter("method");
            //获取指定类的字节码对象
            Class<? extends BaseServlet> clazz = this.getClass();  //这里this指的是继承的BaseServlet对象
            //通过类的字节码对象获取方法的字节码对象
            Method method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //让方法执行
            method.invoke(this, request, response);

            //Method method =  this.getClass().getDeclaredMethods(methodName,HttpServletRequest.class,HttpServletResponse.class);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
