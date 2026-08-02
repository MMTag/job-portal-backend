package com.eazybytes.jobportal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.eazybytes.jobportal.entity.Contact}
 */
@Value
public class ContactRequestDto implements Serializable {
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email")
    String email;
    @NotBlank(message = "Message cannot be empty")
    @Size(min = 5, max = 500, message = "Message must be between 5 and 500 characters")
    String message;
    @NotBlank(message = "Name cannot be empty")
    @Size(min = 5, max = 30, message = "Name must be between 5 and 30 characters")
    String name;
    @NotBlank(message = "Subject cannot be empty")
    @Size(min = 5, max = 150, message = "Subject must be between 5 and 150 characters")
    String subject;
    @NotBlank(message = "User type cannot be empty")
    @Pattern(regexp = "Job Seeker|Employer|Other",message = "UserType must be one of Job Seeker, Employer, Other")
    String userType;
}