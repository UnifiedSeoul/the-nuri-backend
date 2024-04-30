package com.nuri.backend.controller;

import com.nuri.backend.dto.JobInfoDto;
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

    @GetMapping("/api/test")
    public ResponseEntity<JobInfoTotalResponse> testJob() {
        JobInfoTotalResponse jobInfoTotalResponse = jobInfoSchedulingService.fetchJobs();
        return ResponseEntity.ok(jobInfoTotalResponse);
    }

    @GetMapping("/api/jobs")
    public ResponseEntity<List<JobInfoDto>> getJobs() {
        List<JobInfoDto> jobInfoList = jobInfoService.getAllJobs();
        return ResponseEntity.ok(jobInfoList);
    }

    @GetMapping("/api/job/{jobId}")
    public ResponseEntity<JobInfoDto> getJobs(@PathVariable("jobId") String jobId) {

        JobInfoDto jobInfoDto = jobInfoService.getJobByJobId(jobId);
        return ResponseEntity.ok(jobInfoDto);
    }
}
