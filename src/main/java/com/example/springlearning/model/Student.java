package com.example.springlearning.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToOne(mappedBy = "student")
    private StudentProfile profile;

    public Student() {
    }

    public Student(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Course getCourse() {
        return course;
    }

    public StudentProfile getProfile() {
        return profile;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setProfile(StudentProfile profile) {
        this.profile = profile;
    }
}