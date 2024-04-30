package com.nuri.backend.service;

import com.nuri.backend.domain.JobInfo;
import com.nuri.backend.dto.JobInfoDto;
import com.nuri.backend.exception.ErrorCode;
import com.nuri.backend.exception.JobInfoException;
import com.nuri.backend.repository.JobInfoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
    public JobInfoDto getJobByJobId(String jobId) {
        JobInfo jobInfo = jobInfoRepository.findById(jobId)
                .orElseThrow(() -> new JobInfoException(ErrorCode.INVALID_JOB_ID));
        return JobInfoDto.from(jobInfo);
    }
}
