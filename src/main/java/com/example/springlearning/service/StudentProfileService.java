package com.example.springlearning.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springlearning.exception.StudentProfileNotFoundException;
import com.example.springlearning.model.StudentProfile;
import com.example.springlearning.repository.StudentProfileJpaRepository;

@Service
public class StudentProfileService {

    private final StudentProfileJpaRepository profileRepository;

    public StudentProfileService(
            StudentProfileJpaRepository profileRepository) {

        this.profileRepository = profileRepository;
    }

    public List<StudentProfile> getProfiles() {
        return profileRepository.findAll();
    }

    public StudentProfile getProfileById(int id) {

        return profileRepository.findById(id)
                .orElseThrow(() ->
                        new StudentProfileNotFoundException(id));
    }

    public StudentProfile createProfile(StudentProfile profile) {
        return profileRepository.save(profile);
    }

    public void deleteProfile(int id) {

        if (!profileRepository.existsById(id)) {
            throw new StudentProfileNotFoundException(id);
        }

        profileRepository.deleteById(id);
    }
}