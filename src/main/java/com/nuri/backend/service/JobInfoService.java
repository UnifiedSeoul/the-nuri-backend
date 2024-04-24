package com.nuri.backend.service;

import com.nuri.backend.domain.JobInfo;
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
    public List<JobInfo> getAllJobs() {
        return jobInfoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public JobInfo getJobByJobId(String jobId) {
        return jobInfoRepository.findById(jobId).orElseThrow();
    }
}
