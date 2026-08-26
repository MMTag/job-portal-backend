package com.eazybytes.jobportal.user.service;

import com.eazybytes.jobportal.dto.ProfileDto;
import com.eazybytes.jobportal.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface IUserService {
    Optional<UserDto> findUserByEmail(String email);
    UserDto elevateToEmployer(Long id);
    UserDto assignCompanyToEmployer(Long userId, Long companyId);
    ProfileDto createOrUpdateProfile(String userEmail, String profileJson, MultipartFile profilePicture, MultipartFile resume);
    ProfileDto getProfile(String userEmail);
    ProfileDto getProfilePicture(String userEmail);
    ProfileDto getResume(String userEmail);
}
