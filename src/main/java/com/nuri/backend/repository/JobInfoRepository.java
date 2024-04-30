package com.nuri.backend.repository;

import com.nuri.backend.domain.JobInfo;
import com.nuri.backend.dto.JobInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobInfoRepository extends JpaRepository<JobInfo, String> {
    Page<JobInfo> findAll(Pageable pageable);

}
