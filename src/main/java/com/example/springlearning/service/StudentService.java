package com.example.springlearning.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springlearning.exception.StudentCourseNotFoundException;
import com.example.springlearning.exception.StudentNotFoundException;
import com.example.springlearning.model.Course;
import com.example.springlearning.model.Student;
import com.example.springlearning.repository.StudentJpaRepository;
import com.example.springlearning.repository.StudentProfileJpaRepository;

@Service
public class StudentService {

    private static final Logger logger =
            LoggerFactory.getLogger(StudentService.class);

    private final StudentJpaRepository studentRepository;
    private final StudentProfileJpaRepository profileRepository;

    public StudentService(
            StudentJpaRepository studentRepository,
            StudentProfileJpaRepository profileRepository) {

        this.studentRepository = studentRepository;
        this.profileRepository = profileRepository;
    }

    public List<Student> getStudents() {
        logger.info("Fetching all students");
        return studentRepository.findAllWithCourses();
    }
    
    public Page<Student> getStudents(Pageable pageable) {

        logger.info(
                "Fetching students - page: {}, size: {}",
                pageable.getPageNumber(),
                pageable.getPageSize()
        );

        return studentRepository.findAll(pageable);
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

        Student existingStudent =
                studentRepository.findById(student.getId())
                        .orElseThrow(() ->
                                new StudentNotFoundException(
                                        student.getId()));

        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setCourses(student.getCourses());

        return studentRepository.save(existingStudent);
    }

    public void deleteStudent(int id) {
        logger.info("Deleting student with ID: {}", id);

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error(
                            "Cannot delete student with ID {} because it does not exist",
                            id);

                    return new StudentNotFoundException(id);
                });

        if (student.getProfile() != null) {
            profileRepository.delete(student.getProfile());
        }

        studentRepository.delete(student);

        logger.info("Student with ID {} deleted successfully", id);
    }
    
    @Transactional
    public Student addCourseToStudent(
            int studentId,
            int courseId,
            Course course) {

        Student student = getStudentById(studentId);

        student.addCourse(course);

        return studentRepository.save(student);
    }
    
    @Transactional
    public Student removeCourseFromStudent(
            int studentId,
            int courseId) {

        Student student = getStudentById(studentId);

        Course course = student.getCourses()
                .stream()
                .filter(c -> c.getId() == courseId)
                .findFirst()
                .orElseThrow(() ->
                        new StudentCourseNotFoundException(
                                studentId,
                                courseId
                        ));

        student.removeCourse(course);

        return studentRepository.save(student);
    }
}