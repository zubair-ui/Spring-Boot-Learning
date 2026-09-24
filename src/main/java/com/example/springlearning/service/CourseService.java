package com.example.springlearning.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springlearning.exception.CourseNotFoundException;
import com.example.springlearning.model.Course;
import com.example.springlearning.model.Student;
import com.example.springlearning.repository.CourseJpaRepository;
import com.example.springlearning.repository.StudentJpaRepository;

@Service
public class CourseService {

    private final CourseJpaRepository courseRepository;
    private final StudentJpaRepository studentRepository;

    public CourseService(
            CourseJpaRepository courseRepository,
            StudentJpaRepository studentRepository) {

        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(int id) {

        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Student> getStudentsByCourseId(int courseId) {

        getCourseById(courseId);

        return studentRepository.findByCoursesId(courseId);
    }

    public Course updateCourse(Course course) {

        Course existingCourse =
                courseRepository.findById(course.getId())
                        .orElseThrow(() ->
                                new CourseNotFoundException(
                                        course.getId()));

        existingCourse.setName(course.getName());

        return courseRepository.save(existingCourse);
    }

    @Transactional
    public Course patchCourse(int id, String name) {

        Course existingCourse = getCourseById(id);

        if (name != null) {
            existingCourse.setName(name);
        }

        return existingCourse;
    }
    
    @Transactional
    public void deleteCourse(int id) {

        Course course = getCourseById(id);

        List<Student> students =
                studentRepository.findByCoursesId(id);

        for (Student student : students) {
            student.getCourses().remove(course);
        }

        studentRepository.saveAll(students);

        courseRepository.delete(course);
    }
}