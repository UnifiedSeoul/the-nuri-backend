package com.nuri.backend.service;

import com.nuri.backend.domain.JobInfo;

import com.nuri.backend.dto.api.job.JobCustomResponse;
import com.nuri.backend.entity.UserJobInfo;
import com.nuri.backend.repository.JobInfoRepository;

import java.util.ArrayList;
import com.nuri.backend.dto.JobInfoDto;
import com.nuri.backend.exception.ErrorCode;
import com.nuri.backend.exception.JobInfoException;


import java.util.List;

import com.nuri.backend.repository.UserJobInfoRepository;
import com.nuri.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.*;

import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class JobInfoService {

    private final JobInfoRepository jobInfoRepository;

    private final UserJobInfoRepository userJobInfoRepository;

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<JobInfoDto> getAllJobs() {
        List<JobInfoDto> jobInfoList = jobInfoRepository.findAll().stream()
                .map(JobInfoDto::from)
                .toList();

        return jobInfoList;
    }

    @Transactional(readOnly = true)
    public List<JobInfoDto> getPageJobs(Pageable pageable) {
        List<JobInfoDto> jobInfoList = jobInfoRepository.findAll(pageable).stream()
                .map(JobInfoDto::from)
                .toList();


        return jobInfoList;
    }

    @Transactional(readOnly = true)
    public JobInfoDto getJobByJobId(String jobId) {
        JobInfo jobInfo = jobInfoRepository.findById(jobId)
                .orElseThrow(() -> new JobInfoException(ErrorCode.INVALID_JOB_ID));
        return JobInfoDto.from(jobInfo);
    }

    @Transactional(readOnly = true)
    public List<JobInfoDto> getJobsByDistance(String x, String y, String distance) {

        double longitude = Double.parseDouble(x);
        double latitude = Double.parseDouble(y);
        double dist = Double.parseDouble(distance);
        double distanceInDegrees = dist / 111.32;

        return jobInfoRepository.findJobsWithinDistance(longitude, latitude, distanceInDegrees)
                .stream()
                .map(JobInfoDto::from)
                .toList();
    }


    @Transactional(readOnly = true)
    public List<JobInfoDto> getCustomJob(String username){
        List<UserJobInfo> userJobs = userJobInfoRepository.findAllByUserId(userRepository.findByUsername(username).getId());
        List<JobInfoDto> customJob = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        String baseUrl = "${custom.url}";

        // HttpHeaders 객체 생성 및 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<List<UserJobInfo>> request = new HttpEntity<>(userJobs, headers);

        // HttpEntity 객체 생성 (사용자 작업 정보 포함)
        ResponseEntity<JobCustomResponse> response = restTemplate.exchange(
                baseUrl,
                HttpMethod.POST,
                request,
                JobCustomResponse.class
        );

        List<String> jobIdList = response.getBody().getRecommendedjobid();

        for(String jobId : jobIdList){
            JobInfoDto jobInfoDto= JobInfoDto.from(jobInfoRepository.findById(jobId).orElseThrow());
            customJob.add(jobInfoDto);
        }

        return customJob;
    }
}
