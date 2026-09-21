package com.example.springlearning.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.springlearning.model.Student;

@Repository
public class StudentRepository {

    private final List<Student> students = new ArrayList<>();

    public StudentRepository() {
        students.add(new Student(1, "Zubair", "zubair@example.com"));
        students.add(new Student(2, "Ali", "ali@example.com"));
        students.add(new Student(3, "Ahmed", "ahmed@example.com"));
    }

    public Student getStudent() {
        return students.get(0);
    }

    public List<Student> getStudents() {
        return students;
    }
    
    public Student findById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }
    
    public Student save(Student student) {
        students.add(student);
        return student;
    }
    
    public Student update(Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == updatedStudent.getId()) {
                students.set(i, updatedStudent);
                return updatedStudent;
            }
        }

        return null;
    }
    
    public boolean deleteById(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.remove(i);
                return true;
            }
        }

        return false;
    }
}