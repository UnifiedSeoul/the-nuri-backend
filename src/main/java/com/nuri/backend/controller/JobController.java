package com.nuri.backend.controller;

import com.nuri.backend.dto.JobInfoDto;
import com.nuri.backend.service.JobInfoSchedulingService;
import com.nuri.backend.service.JobInfoService;

import java.util.List;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JobController {

    private final JobInfoService jobInfoService;
    private final JobInfoSchedulingService jobInfoSchedulingService;

    @GetMapping(value = "/api/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<JobInfoDto>> testJob() {
        return ResponseEntity.ok(jobInfoSchedulingService.fetchJobs());
    }

    @GetMapping("/api/jobsAll")
    public ResponseEntity<List<JobInfoDto>> getJobs() {
        List<JobInfoDto> jobInfoList = jobInfoService.getAllJobs();
        return ResponseEntity.ok(jobInfoList);
    }

    @GetMapping("/api/jobs")
    public ResponseEntity<List<JobInfoDto>> getPageJobs(@PageableDefault(page=1, size= 16, sort ="jobId",direction = Sort.Direction.DESC) Pageable pageable) {
        List<JobInfoDto> jobInfoList = jobInfoService.getPageJobs(pageable);
        return ResponseEntity.ok(jobInfoList);
    }


    @GetMapping("/api/job/{jobId}")
    public ResponseEntity<JobInfoDto> getJobById(@PathVariable("jobId") String jobId) {

        JobInfoDto jobInfoDto = jobInfoService.getJobByJobId(jobId);
        return ResponseEntity.ok(jobInfoDto);
    }

    @GetMapping("/api/job")
    public ResponseEntity<List<JobInfoDto>> getNearJobs(@RequestParam(name = "x") String x,
            @RequestParam(name = "y") String y,
            @RequestParam(name = "distance") String distance) {

        return ResponseEntity.ok(jobInfoService.getJobsByDistance(x, y, distance));
    }
}

