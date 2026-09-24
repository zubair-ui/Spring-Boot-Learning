package com.example.springlearning.dto;

public class CourseStudentCountResponse {

    private int courseId;
    private String courseName;
    private long studentCount;

    public CourseStudentCountResponse(
            int courseId,
            String courseName,
            long studentCount) {

        this.courseId = courseId;
        this.courseName = courseName;
        this.studentCount = studentCount;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public long getStudentCount() {
        return studentCount;
    }
}