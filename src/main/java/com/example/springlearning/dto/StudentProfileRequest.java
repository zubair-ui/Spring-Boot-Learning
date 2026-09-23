package com.example.springlearning.dto;

public class StudentProfileRequest {

    private String phone;
    private String address;
    private int studentId;

    public StudentProfileRequest() {
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}