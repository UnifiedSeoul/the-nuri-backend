package com.nuri.backend.controller;

import com.nuri.backend.domain.JobInfo;
import com.nuri.backend.dto.api.job.JobInfoTotalResponse;
import com.nuri.backend.service.JobInfoSchedulingService;
import com.nuri.backend.service.JobInfoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
