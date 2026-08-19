package com.eazybytes.jobportal.job.service;

import com.eazybytes.jobportal.dto.JobDto;

import java.util.List;

public interface IJobsService {
    List<JobDto> getEmployerJobs(String employerEmail);
    JobDto updateJobStatus(String employerEmail, Long jobId, String status);
    JobDto createJob(JobDto jobDto,String employerEmail);
}
