package com.eazybytes.jobportal.repository;

import com.eazybytes.jobportal.entity.JobPortalUser;
import com.eazybytes.jobportal.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
}
