package com.gdupt.hai.dao;

import com.gdupt.hai.domain.Employee;
import com.gdupt.hai.domain.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeDao {

    //查询所有员工
    List<Employee> selectAllEmp();

    //查询所有员工，分页显示
    public  List<Employee> selectAllEmp(Map<String,Object> params);

    //查询总数
    public int count();

    //通过姓名查找员工
    List<Employee> selectEmpByName(String name);

    //添加员工
    Boolean addEmploy();

    //删除员工信息
    int deleteEmp(String empNo);

    //修改员工信息
    int editEmp(Employee employee);

    //判断添加的新员工是否已存在
    Employee isEmpExist(@Param("eno")String eno,@Param("idNo")String idNo);

    //添加新员工
    int addEmployee(Employee employee);

    //获取部门编号和部门
    List<Map<String,Object>> getDept();

}
