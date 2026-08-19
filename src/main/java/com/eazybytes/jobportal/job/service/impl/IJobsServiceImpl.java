package com.eazybytes.jobportal.job.service.impl;

import com.eazybytes.jobportal.dto.JobDto;
import com.eazybytes.jobportal.entity.Job;
import com.eazybytes.jobportal.entity.JobPortalUser;
import com.eazybytes.jobportal.job.service.IJobsService;
import com.eazybytes.jobportal.repository.CompanyRepository;
import com.eazybytes.jobportal.repository.JobPortalUserRepository;
import com.eazybytes.jobportal.repository.JobRepository;
import com.eazybytes.jobportal.util.ApplicationUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IJobsServiceImpl implements IJobsService {
    private final JobPortalUserRepository jobPortalUserRepository;
    private final JobRepository jobRepository;
    @Override
    public List<JobDto> getEmployerJobs(String employerEmail) {
        JobPortalUser employer = jobPortalUserRepository.findJobPortalUserByEmail(employerEmail)
                .orElseThrow(() -> new RuntimeException("Employer not found"));

        if (employer.getCompany() == null) {
            throw new RuntimeException("Employer does not have a company assigned");
        }

        List<Job> jobs = employer.getCompany().getJobs();
        return jobs.stream()
                .map(ApplicationUtility::transformJobToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public JobDto updateJobStatus(String employerEmail, Long jobId, String status) {
        if (!status.equals("ACTIVE") && !status.equals("CLOSED") && !status.equals("DRAFT")) {
            throw new RuntimeException("Invalid status. Must be ACTIVE, CLOSED, or DRAFT");
        }
        JobPortalUser employer = jobPortalUserRepository.findJobPortalUserByEmail(employerEmail)
                .orElseThrow(() -> new RuntimeException("Employer not found"));

        if (employer.getCompany() == null) {
            throw new RuntimeException("Employer does not have a company assigned");
        }
        Job job = employer.getCompany().getJobs().stream().filter(j -> j.getId().equals(jobId)).findFirst()
                .orElseThrow(() -> new RuntimeException("Job not found"));
        if(job == null){
            throw new RuntimeException("No jobs found with Id: "+jobId);
        }
        job.setStatus(status);
        return ApplicationUtility.transformJobToDto(job);
    }

    @Transactional
    @Override
    public JobDto createJob(JobDto jobDto,String employerEmail) {
        // Validate employer and get their company
        JobPortalUser employer = jobPortalUserRepository.findJobPortalUserByEmail(employerEmail)
                .orElseThrow(() -> new RuntimeException("Employer not found"));
        if (employer.getCompany() == null) {
            throw new RuntimeException("Employer does not have a company assigned. Please contact admin.");
        }
        Job job = transformDtoToEntity(jobDto);
        job.setPostedDate(Instant.now());
        job.setApplicationsCount(0);
        job.setStatus("DRAFT");
        job.setCompany(employer.getCompany());
        Job savedJob = jobRepository.save(job);
        return ApplicationUtility.transformJobToDto(savedJob);
    }

    private Job transformDtoToEntity(JobDto jobDto) {
        Job job = new Job();
        BeanUtils.copyProperties(jobDto, job);
        return job;
    }
}
