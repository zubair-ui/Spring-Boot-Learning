package com.example.springlearning.dto;

public class StudentProfileResponse {

    private int id;
    private String phone;
    private String address;
    private int studentId;

    public StudentProfileResponse(
            int id,
            String phone,
            String address,
            int studentId) {

        this.id = id;
        this.phone = phone;
        this.address = address;
        this.studentId = studentId;
    }

    public int getId() {
        return id;
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
}