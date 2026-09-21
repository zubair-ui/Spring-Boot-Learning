package com.example.springlearning.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springlearning.exception.StudentNotFoundException;
import com.example.springlearning.model.Student;
import com.example.springlearning.repository.StudentJpaRepository;	

@Service
public class StudentService {

	private final StudentJpaRepository studentRepository;

    public StudentService(StudentJpaRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Student student) {
        Student existingStudent = studentRepository.findById(student.getId())
                .orElseThrow(() -> new StudentNotFoundException(student.getId()));

        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());

        return studentRepository.save(existingStudent);
    }

    public void deleteStudent(int id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }

        studentRepository.deleteById(id);
    }
}