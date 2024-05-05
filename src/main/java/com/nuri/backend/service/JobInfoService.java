package com.nuri.backend.service;

import com.nuri.backend.domain.JobInfo;
import com.nuri.backend.entity.UserJobInfo;
import com.nuri.backend.repository.JobInfoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.nuri.backend.repository.UserJobInfoRepository;
import com.nuri.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.print.attribute.standard.JobName;

@Service
@RequiredArgsConstructor
public class JobInfoService {

    private final JobInfoRepository jobInfoRepository;

    private final UserJobInfoRepository userJobInfoRepository;

    private final UserRepository userRepository;

    private final WebClient
    @Transactional(readOnly = true)
    public List<JobInfo> getAllJobs() {
        return jobInfoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public JobInfo getJobByJobId(String jobId) {
        return jobInfoRepository.findById(jobId).orElseThrow();
    }


    @Transactional(readOnly = true)
    public List<JobInfo> getCustomJob(String username){
        List<UserJobInfo> userJobs = userJobInfoRepository.findAllById(userRepository.findByUsername(username).getId());
        List<JobInfo> customJob = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        String baseUrl = "http://external-server.com/api/jobs";

        // HttpHeaders 객체 생성 및 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<List<UserJobInfo>> request = new HttpEntity<>(userJobs, headers);


        // HttpEntity 객체 생성 (사용자 작업 정보 포함)
        ResponseEntity<List<String>> response = restTemplate.exchange(
                baseUrl,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<List<String>>() {}
        );

        List<String> jobIdList = response.getBody();

        for(String jobId : jobIdList){
            JobInfo jobInfo= jobInfoRepository.findById(jobId).orElseThrow();
            customJob.add(jobInfo);
        }

        return customJob;
    }
}
