package com.gdupt.hai.service.impl;

import com.gdupt.hai.dao.EmployeeDao;
import com.gdupt.hai.domain.Employee;
import com.gdupt.hai.domain.Pager;
import com.gdupt.hai.service.EmployeeService;
import com.gdupt.hai.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements EmployeeService {

    SqlSession sqlSession = null;
    EmployeeDao dao = null;

    //获取所有员工信息
    @Override
    public List<Employee> getEmp() {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(EmployeeDao.class);
        List<Employee> employeeList = dao.selectAllEmp();
        sqlSession.close();
        return employeeList;
    }

    @Override
    public Pager<Employee> getAllEmp(int page, int size) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(EmployeeDao.class);
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("page",(page-1)*size);
        params.put("size",size);
        Pager<Employee> pager = new Pager<Employee>();
        List<Employee> employeeList = dao.selectAllEmp(params);
        pager.setRows(employeeList);
        pager.setTotal(dao.count());
        sqlSession.commit();
        return pager;
    }

    //通过查询员工姓名获得信息
    @Override
    public List<Employee> getEmpByName(String name) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(EmployeeDao.class);
        List<Employee> list = dao.selectEmpByName(name);
        sqlSession.close();
        return list;
    }

    /**
     * 通过员工编号删除相应的员工信息
     * @param empNo
     * @return
     */
    @Override
    public int deleteEmployee(String empNo) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(EmployeeDao.class);
        int result = dao.deleteEmp(empNo);
        sqlSession.commit();
        sqlSession.close();
        return result;
    }

    /**
     * 通过员工编号修改相应的员工信息
     * @param employee
     * @return
     */
    @Override
    public int editEmployee(Employee employee) {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(EmployeeDao.class);
        int result = dao.editEmp(employee);
        sqlSession.commit();
        sqlSession.close();
        return result;
    }

    /**
     * 判断要添加的员工是否已经存在
     */
    @Override
    public Employee isExistEmp(String eno,String idNo){
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(EmployeeDao.class);
        return dao.isEmpExist(eno,idNo);
    }

    /**
     * 添加新员工
     */
    @Override
    public int addNewEmp(Employee employee){
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(EmployeeDao.class);
        int result = dao.addEmployee(employee);
        sqlSession.commit();
        sqlSession.close();
        return result;
    }

    @Override
    public List<Map<String, Object>> getDeptList() {
        sqlSession = MyBatisUtils.getSqlSession();
        dao = sqlSession.getMapper(EmployeeDao.class);
        List<Map<String,Object>> list = dao.getDept();
        sqlSession.close();
        return list;
    }


}
