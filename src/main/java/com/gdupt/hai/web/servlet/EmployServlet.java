package com.gdupt.hai.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gdupt.hai.domain.Employee;
import com.gdupt.hai.domain.Pager;
import com.gdupt.hai.service.EmployeeService;
import com.gdupt.hai.service.impl.EmployeeServiceImpl;
import com.gdupt.hai.web.baseServlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "EmployServlet")
public class EmployServlet extends BaseServlet {

    private EmployeeService service ;
    private Employee employee;

    /**
     * 获取全部员工列表
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    public void getAllEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        //String a = request.getParameter("page");
        int page = Integer.parseInt(request.getParameter("page"));
        int size = Integer.parseInt(request.getParameter("size"));
        System.out.println("page=" + page + " " + "size=" + size);
        service = new EmployeeServiceImpl();
        Pager<Employee> pager = service.getAllEmp(page,size);
        List<Map<String,Object>> list = service.getDeptList();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("empList",pager);
        if (pager != null){
            jsonObject.put("status",200);
            jsonObject.put("deptList",list);
        }else {
            jsonObject.put("status",400);
        }
        writer.println(jsonObject);
        writer.flush();
        writer.close();
    }

    /**
     * 通过姓名查询相应员工信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    public void getEmpByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String name = request.getParameter("ename");
        System.out.println("++++"+name+"++++");
        JSONObject jsonObject = new JSONObject();
        service = new EmployeeServiceImpl();
        List<Employee> list = service.getEmpByName(name);
        jsonObject.put("emp",list);
        if(list.isEmpty()){
            jsonObject.put("status",400);
            response.getWriter().println(jsonObject);
        }else {
            jsonObject.put("status",200);
            response.getWriter().println(jsonObject);
        }
    }

    /**
     * 通过员工编号删除相应的员工信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteEmp(HttpServletRequest request,HttpServletResponse response) throws  ServletException,IOException{
        String empNo = request.getParameter("empNo");
        System.out.println("++++"+empNo+"++++");
        JSONObject object = new JSONObject();
        EmployeeService service = new EmployeeServiceImpl();
        int result = service.deleteEmployee(empNo);
        if (result != 0){
            object.put("status",200);
        }else{
            object.put("status",400);
        }
        response.getWriter().print(object);
    }

    /**
     * 修改员工信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void updateEmp(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String empForm = request.getParameter("empForm");
        JSONObject jsonObject = new JSONObject();
        Employee employee = JSON.parseObject(empForm,Employee.class);
        EmployeeService service = new EmployeeServiceImpl();
        int result = service.editEmployee(employee);
        if (result != 0){
            jsonObject.put("status",200);
        }else {
            jsonObject.put("status",400);
        }
        response.getWriter().print(jsonObject);
    }

    /**
     * 添加员工信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addNewEmp(HttpServletRequest request,HttpServletResponse response) throws Exception{
        String empForm = request.getParameter("empForm");
        JSONObject object = new JSONObject();
        EmployeeService service = new EmployeeServiceImpl();
        Employee employee = JSON.parseObject(empForm,Employee.class);
        Employee employee1 = service.isExistEmp(employee.getEno(),employee.getIdNo());
        if (employee1 != null){
            object.put("status",100);  //需要添加的员工信息已存在
        }else {
            int result = service.addNewEmp(employee);
            if (result != 0){
                object.put("status",200);
            }
            else {
                object.put("status",400);
            }
        }
        response.getWriter().print(object);
    }

}
