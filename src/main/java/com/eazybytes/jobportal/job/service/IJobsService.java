package com.eazybytes.jobportal.job.service;

import com.eazybytes.jobportal.dto.JobApplicationDto;
import com.eazybytes.jobportal.dto.JobDto;
import com.eazybytes.jobportal.dto.UpdateJobApplicationDto;

import java.util.List;

public interface IJobsService {
    List<JobDto> getEmployerJobs(String employerEmail);
    JobDto updateJobStatus(String employerEmail, Long jobId, String status);
    JobDto createJob(JobDto jobDto,String employerEmail);
    List<JobApplicationDto> getApplicationsByJobForEmployer(Long jobId);

    /**
     * Updates an existing job application with the provided details.
     *
     * @param updateJobApplicationDto the data transfer object containing
     *                                the details to update the job application,
     *                                such as the application ID, status, and notes.
     * @return true if the job application was successfully updated; false otherwise.
     */
    boolean updateJobApplication(UpdateJobApplicationDto updateJobApplicationDto);
}
