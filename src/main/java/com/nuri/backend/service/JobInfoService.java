package com.nuri.backend.service;

import com.nuri.backend.domain.JobInfo;
import com.nuri.backend.dto.JobInfoDto;
import com.nuri.backend.exception.ErrorCode;
import com.nuri.backend.exception.JobInfoException;
import com.nuri.backend.repository.JobInfoRepository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JobInfoService {

    private final JobInfoRepository jobInfoRepository;

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
}
