package com.nuri.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nuri.backend.domain.JobInfo;
import com.nuri.backend.dto.JobInfoDto;
import com.nuri.backend.dto.api.login.UserJobInfoDTO;
import com.nuri.backend.exception.ErrorCode;
import com.nuri.backend.exception.JobInfoException;
import com.nuri.backend.repository.JobInfoRepository;
import com.nuri.backend.repository.UserJobInfoRepository;
import com.nuri.backend.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class JobInfoService {

    private final UserRepository userRepository;
    private final JobInfoRepository jobInfoRepository;
    private final UserJobInfoRepository userJobInfoRepository;

    @Transactional(readOnly = true)
    public List<JobInfoDto> getPageJobs(Pageable pageable) {

        return jobInfoRepository.findAll(pageable).stream()
                .map(JobInfoDto::from)
                .toList();
    }

    @Transactional
    public JobInfoDto getJobByJobId(String jobId) {
        jobInfoRepository.incrementViewCount(jobId);
        JobInfo jobInfo = jobInfoRepository.findById(jobId)
                .orElseThrow(() -> new JobInfoException(ErrorCode.INVALID_JOB_ID));
        return JobInfoDto.from(jobInfo);
    }

    @Transactional
    public void incrementHomePageViewCount(String jobId) {
        jobInfoRepository.incrementHomePageViewCount(jobId);
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
        List<UserJobInfoDTO> userJobs = userJobInfoRepository.findAllByUserId(userRepository.findByUsername(username).getId())
                .stream()
                .map(UserJobInfoDTO::from)
                .toList();

        List<JobInfoDto> customJob = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        String baseUrl = "http://3.38.116.43:5000/api/recommend";

        // HttpHeaders 객체 생성 및 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<List<UserJobInfoDTO>> request = new HttpEntity<>(userJobs, headers);

        // HttpEntity 객체 생성 (사용자 작업 정보 포함)
        ResponseEntity<String> response = restTemplate.exchange(
                baseUrl,
                HttpMethod.POST,
                request,
                String.class
        );

        System.out.println(response.getBody());

        String responseBody = response.getBody();
        if (responseBody != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                JsonNode jsonNode = mapper.readTree(responseBody);
                if (jsonNode.has("recommendJobId")) {
                    JsonNode recommendJobIdNode = jsonNode.get("recommendJobId");
                    if (recommendJobIdNode.isArray()) {
                        List<String> jobIdList = new ArrayList<>();
                        for (JsonNode node : recommendJobIdNode) {
                            jobIdList.add(node.asText());
                        }
                        for (String jobId : jobIdList) {
                            JobInfoDto jobInfoDto = JobInfoDto.from(jobInfoRepository.findById(jobId).orElseThrow());
                            customJob.add(jobInfoDto);
                        }
                    }
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return customJob;
    }
}
