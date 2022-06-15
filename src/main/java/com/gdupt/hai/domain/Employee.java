package com.gdupt.hai.domain;

public class Employee {

    private String eno;  //员工编号
    private String name;
    private String idNo;   //身份证号码
    private char sex;
    private int age;
    private String birth;
    private String telephone;
    private String dept;  //部门
    private String deptNo; //部门编号
    private String address;
    private String workDate;   //入职日期
    private String job;

    public String getEno() {
        return eno;
    }

    public void setEno(String eno) {
        this.eno = eno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "  eno='" + eno + '\'' +
                ", name='" + name + '\'' +
                ", idNo='" + idNo + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", birth='" + birth + '\'' +
                ", telephone='" + telephone + '\'' +
                ", dept='" + dept + '\'' +
                ", deptNo='" + deptNo + '\'' +
                ", address='" + address + '\'' +
                ", workDate='" + workDate + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
