package com.example.springlearning.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springlearning.exception.StudentProfileNotFoundException;
import com.example.springlearning.model.Student;
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

    public StudentProfile updateProfile(StudentProfile profile) {

        StudentProfile existingProfile =
                getProfileById(profile.getId());

        existingProfile.setPhone(profile.getPhone());
        existingProfile.setAddress(profile.getAddress());

        if (existingProfile.getStudent() != null) {
            existingProfile.getStudent().setProfile(null);
        }

        existingProfile.setStudent(profile.getStudent());

        if (profile.getStudent() != null) {
            profile.getStudent().setProfile(existingProfile);
        }

        return profileRepository.save(existingProfile);
    }

    public StudentProfile patchProfile(
            int id,
            String phone,
            String address,
            Student student) {

        StudentProfile existingProfile =
                getProfileById(id);

        if (phone != null) {
            existingProfile.setPhone(phone);
        }

        if (address != null) {
            existingProfile.setAddress(address);
        }

        if (student != null) {

            if (existingProfile.getStudent() != null) {
                existingProfile.getStudent().setProfile(null);
            }

            existingProfile.setStudent(student);
            student.setProfile(existingProfile);
        }

        return profileRepository.save(existingProfile);
    }

    public void deleteProfile(int id) {

        if (!profileRepository.existsById(id)) {
            throw new StudentProfileNotFoundException(id);
        }

        profileRepository.deleteById(id);
    }
}