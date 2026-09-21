package com.example.springlearning.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springlearning.exception.StudentNotFoundException;
import com.example.springlearning.model.Student;
import com.example.springlearning.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getStudent() {
        return studentRepository.getStudent();
    }

    public List<Student> getStudents() {
        return studentRepository.getStudents();
    }
    
    public Student getStudentById(int id) {
        Student student = studentRepository.findById(id);

        if (student == null) {
            throw new StudentNotFoundException(id);
        }

        return student;
    }
    
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }
    
    public Student updateStudent(Student student) {
        Student updatedStudent = studentRepository.update(student);

        if (updatedStudent == null) {
            throw new StudentNotFoundException(student.getId());
        }

        return updatedStudent;
    }
    
    public void deleteStudent(int id) {
        boolean deleted = studentRepository.deleteById(id);

        if (!deleted) {
            throw new StudentNotFoundException(id);
        }
    }
}