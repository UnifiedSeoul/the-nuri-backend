package com.nuri.backend.controller;

import com.nuri.backend.dto.JobInfoDto;
import com.nuri.backend.service.JobInfoSchedulingService;
import com.nuri.backend.service.JobInfoService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class JobController {

    private final JobInfoService jobInfoService;
    private final JobInfoSchedulingService jobInfoSchedulingService;

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<JobInfoDto>> testJob() {
        return ResponseEntity.ok(jobInfoSchedulingService.fetchJobs());
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<JobInfoDto>> getPageJobs(@PageableDefault(size= 16, sort ="acceptance.fromAcceptanceDate",direction = Sort.Direction.DESC) Pageable pageable) {
        List<JobInfoDto> jobInfoList = jobInfoService.getPageJobs(pageable);
        return ResponseEntity.ok(jobInfoList);
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<JobInfoDto> getJobById(@PathVariable("jobId") String jobId) {

        JobInfoDto jobInfoDto = jobInfoService.getJobByJobId(jobId);
        return ResponseEntity.ok(jobInfoDto);
    }

    @GetMapping("/job/{jobId}/homepage")
    public ResponseEntity<Void> incrementHomePageViewCount(@PathVariable("jobId") String jobId) {

        jobInfoService.incrementHomePageViewCount(jobId);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/job")
    public ResponseEntity<List<JobInfoDto>> getNearJobs(@RequestParam(name = "x") String x,
            @RequestParam(name = "y") String y,
            @RequestParam(name = "distance") String distance) {

        return ResponseEntity.ok(jobInfoService.getJobsByDistance(x, y, distance));
    }

    @GetMapping("/jobs/custom")
    public ResponseEntity<List<JobInfoDto>> getCustomJob(Authentication authentication){
        String username;
        List<JobInfoDto> jobCustomInfoList =  new ArrayList<>();
        if(authentication != null && authentication.isAuthenticated()){
            username = authentication.getName();
            jobCustomInfoList = jobInfoService.getCustomJob(username);
        }

        return ResponseEntity.ok(jobCustomInfoList);
    }
}

