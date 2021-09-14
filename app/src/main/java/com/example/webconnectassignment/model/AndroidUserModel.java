package com.example.webconnectassignment.model;

public class AndroidUserModel {

    // variables for our course
    // name and description.
    private String userEmail;
    private String userDepartment;

    public AndroidUserModel(String userEmail, String userDepartment) {
        this.userEmail = userEmail;
        this.userDepartment = userDepartment;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserDepartment() {
        return userDepartment;
    }

    public void setUserDepartment(String userDepartment) {
        this.userDepartment = userDepartment;
    }
}
