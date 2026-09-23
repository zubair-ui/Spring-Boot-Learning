package com.example.springlearning.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springlearning.dto.StudentProfileRequest;
import com.example.springlearning.dto.StudentProfileResponse;
import com.example.springlearning.model.Student;
import com.example.springlearning.model.StudentProfile;
import com.example.springlearning.service.StudentProfileService;
import com.example.springlearning.service.StudentService;

@RestController
public class StudentProfileController {

    private final StudentProfileService profileService;
    private final StudentService studentService;

    public StudentProfileController(
            StudentProfileService profileService,
            StudentService studentService) {

        this.profileService = profileService;
        this.studentService = studentService;
    }

    @GetMapping("/student-profiles")
    public List<StudentProfileResponse> getProfiles() {

        return profileService.getProfiles()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/student-profiles/{id}")
    public ResponseEntity<StudentProfileResponse> getProfileById(
            @PathVariable int id) {

        StudentProfile profile =
                profileService.getProfileById(id);

        return ResponseEntity.ok(toResponse(profile));
    }

    @PostMapping("/student-profiles")
    public ResponseEntity<StudentProfileResponse> createProfile(
            @RequestBody StudentProfileRequest request) {

        Student student =
                studentService.getStudentById(request.getStudentId());

        StudentProfile profile = new StudentProfile(
                0,
                request.getPhone(),
                request.getAddress()
        );

        profile.setStudent(student);
        student.setProfile(profile);

        StudentProfile savedProfile =
                profileService.createProfile(profile);

        return ResponseEntity.ok(toResponse(savedProfile));
    }

    @PutMapping("/student-profiles/{id}")
    public ResponseEntity<StudentProfileResponse> updateProfile(
            @PathVariable int id,
            @RequestBody StudentProfileRequest request) {

        Student student =
                studentService.getStudentById(request.getStudentId());

        StudentProfile profile = new StudentProfile(
                id,
                request.getPhone(),
                request.getAddress()
        );

        profile.setStudent(student);
        student.setProfile(profile);

        StudentProfile updatedProfile =
                profileService.updateProfile(profile);

        return ResponseEntity.ok(toResponse(updatedProfile));
    }

    @PatchMapping("/student-profiles/{id}")
    public ResponseEntity<StudentProfileResponse> patchProfile(
            @PathVariable int id,
            @RequestBody StudentProfileRequest request) {

        Student student = null;

        if (request.getStudentId() > 0) {
            student =
                    studentService.getStudentById(
                            request.getStudentId());
        }

        StudentProfile updatedProfile =
                profileService.patchProfile(
                        id,
                        request.getPhone(),
                        request.getAddress(),
                        student
                );

        if (student != null) {
            student.setProfile(updatedProfile);
        }

        return ResponseEntity.ok(toResponse(updatedProfile));
    }

    @DeleteMapping("/student-profiles/{id}")
    public ResponseEntity<Void> deleteProfile(
            @PathVariable int id) {

        profileService.deleteProfile(id);

        return ResponseEntity.noContent().build();
    }

    private StudentProfileResponse toResponse(
            StudentProfile profile) {

        return new StudentProfileResponse(
                profile.getId(),
                profile.getPhone(),
                profile.getAddress(),
                profile.getStudent().getId()
        );
    }
}