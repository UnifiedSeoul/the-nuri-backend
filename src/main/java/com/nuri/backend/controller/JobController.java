package com.nuri.backend.controller;

import com.nuri.backend.domain.JobInfo;
import com.nuri.backend.dto.api.job.JobInfoTotalResponse;
import com.nuri.backend.service.JobInfoSchedulingService;
import com.nuri.backend.service.JobInfoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.JobName;

@RestController
@RequiredArgsConstructor
public class JobController {

    private final JobInfoService jobInfoService;
    private final JobInfoSchedulingService jobInfoSchedulingService;

    @GetMapping("/test")
    public ResponseEntity<JobInfoTotalResponse> testJob() {
        JobInfoTotalResponse jobInfoTotalResponse = jobInfoSchedulingService.fetchJobs();
        return ResponseEntity.ok(jobInfoTotalResponse);
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<JobInfo>> getJobs() {
        List<JobInfo> jobInfoList = jobInfoService.getAllJobs();
        return ResponseEntity.ok(jobInfoList);
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<JobInfo> getJobs(@PathVariable("jobId") String jobId) {
        JobInfo jobByJobId = jobInfoService.getJobByJobId(jobId);
        return ResponseEntity.ok(jobByJobId);
    }

    @GetMapping("/jobs/custom")
    public ResponseEntity<JobInfo> getCustomJob(Authentication authentication){
        String username;
        if(authentication != null && authentication.isAuthenticated()){
            username = authentication.getName();

        }
    }
}
