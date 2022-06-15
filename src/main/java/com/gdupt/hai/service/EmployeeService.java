package com.gdupt.hai.service;

import com.gdupt.hai.domain.Employee;
import com.gdupt.hai.domain.Pager;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    //获得所有员工信息
    public List<Employee> getEmp();

    //获取商品列表，分页操作
    public Pager<Employee> getAllEmp(int page, int size);

    //通过查询姓名获得信息
    public List<Employee> getEmpByName(String name);

    //通过员工编号删除相应的员工信息
    int deleteEmployee(String empNo);

    //通过员工编号修改相应的员工信息
    int editEmployee(Employee employee);

    //判断员工信息是否已存在
    Employee isExistEmp(String eno,String idNo);

    //添加新员工信息
    int addNewEmp(Employee employee);

    //获取所有部门列表
    List<Map<String,Object>> getDeptList();
}
