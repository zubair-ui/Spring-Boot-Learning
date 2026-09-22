package com.example.springlearning.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.springlearning.exception.StudentNotFoundException;
import com.example.springlearning.model.Student;
import com.example.springlearning.repository.StudentJpaRepository;	

@Service
public class StudentService {
	
	private static final Logger logger =
	        LoggerFactory.getLogger(StudentService.class);

	private final StudentJpaRepository studentRepository;

    public StudentService(StudentJpaRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {

        logger.info("Fetching all students");

        return studentRepository.findAll();
    }

    public Student getStudentById(int id) {

        logger.info("Fetching student with ID: {}", id);

        return studentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Student with ID {} was not found", id);
                    return new StudentNotFoundException(id);
                });
    }

    public Student createStudent(Student student) {

        logger.info("Creating student with name: {}", student.getName());

        Student savedStudent = studentRepository.save(student);

        logger.info("Student created with ID: {}", savedStudent.getId());

        return savedStudent;
    }

    public Student updateStudent(Student student) {
        Student existingStudent = studentRepository.findById(student.getId())
                .orElseThrow(() -> new StudentNotFoundException(student.getId()));

        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());

        return studentRepository.save(existingStudent);
    }

    public void deleteStudent(int id) {

        logger.info("Deleting student with ID: {}", id);

        if (!studentRepository.existsById(id)) {
            logger.error("Cannot delete student with ID {} because it does not exist", id);
            throw new StudentNotFoundException(id);
        }

        studentRepository.deleteById(id);

        logger.info("Student with ID {} deleted successfully", id);
    }
}