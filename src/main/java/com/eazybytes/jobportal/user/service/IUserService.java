package com.eazybytes.jobportal.user.service;

import com.eazybytes.jobportal.dto.UserDto;

import java.util.Optional;

public interface IUserService {
    public Optional<UserDto> findUserByEmail(String email);
    UserDto elevateToEmployer(Long id);
    UserDto assignCompanyToEmployer(Long userId, Long companyId);
}
