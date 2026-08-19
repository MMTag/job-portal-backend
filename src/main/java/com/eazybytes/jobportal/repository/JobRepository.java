package com.eazybytes.jobportal.repository;

import com.eazybytes.jobportal.entity.Company;
import com.eazybytes.jobportal.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {
}
