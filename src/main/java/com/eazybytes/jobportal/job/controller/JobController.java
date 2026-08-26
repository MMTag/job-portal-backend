package com.eazybytes.jobportal.job.controller;

import com.eazybytes.jobportal.dto.JobApplicationDto;
import com.eazybytes.jobportal.dto.JobDto;
import com.eazybytes.jobportal.dto.UpdateJobApplicationDto;
import com.eazybytes.jobportal.job.service.IJobsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {
    private final IJobsService jobsService;

    @GetMapping("/employer")
    public ResponseEntity<List<JobDto>> getEmployerJobs(Authentication authentication){
        var employerEmail = authentication.getName();
        List<JobDto> jobs = jobsService.getEmployerJobs(employerEmail);
        return ResponseEntity.status(HttpStatus.OK).body(jobs);
    }

    @PatchMapping("/{jobId}/status/employer")
    public ResponseEntity<?> updateJobStatus(Authentication authentication, @PathVariable Long jobId, @RequestBody Map<String,String> requestBody){
        var employerEmail = authentication.getName();
        var status = requestBody.get("status");
        if (status == null || status.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Status is required"));
        }
        JobDto updatedJob = jobsService.updateJobStatus(employerEmail,jobId,status.toUpperCase());
        return ResponseEntity.status(HttpStatus.OK).body(updatedJob);
    }

    @PostMapping("/employer")
    public ResponseEntity<JobDto> createJob(@RequestBody @Valid JobDto jobDto, Authentication authentication){
        var employerEmail = authentication.getName();
        JobDto createdJobDto = jobsService.createJob(jobDto,employerEmail);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdJobDto);
    }


    @GetMapping("/applications/{jobId}/employer")
    public ResponseEntity<List<JobApplicationDto>> getApplicationsByJobForEmployer(
            @PathVariable Long jobId) {
        List<JobApplicationDto> applications = jobsService.getApplicationsByJobForEmployer(jobId);
        return ResponseEntity.ok(applications);
    }

    @PatchMapping("/applications/employer")
    public ResponseEntity<String> updateJobApplication(
            @RequestBody @Valid UpdateJobApplicationDto updateJobApplicationDto) {
        boolean isUpdated = jobsService.updateJobApplication(updateJobApplicationDto);
        if(!isUpdated) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update application");
        }
        return ResponseEntity.ok("Application updated successfully");
    }
}
