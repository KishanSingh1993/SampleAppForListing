package com.example.webconnectassignment.model;

public class Employee {

    private long empId;
    private String department;
    private String Email;
    private String gender;
    private String hireDate;
    private String Mobile;

    public Employee() {
    }

    public Employee(long empId, String department, String emial, String mobile) {
        this.empId = empId;
        this.department = department;
        this.Email = emial;

        this.Mobile = mobile;
    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String firstName) {
        this.department = firstName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }



    public String toString() {
        return "Emp id: " + getEmpId() + "\n" + "Name: " + getDepartment()+"\n" + "Email:" + getEmail() + "\n" + "Mobile: " + getMobile();
    }

}
