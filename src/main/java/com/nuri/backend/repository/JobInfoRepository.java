package com.nuri.backend.repository;

import com.nuri.backend.domain.JobInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobInfoRepository extends JpaRepository<JobInfo, String> {

}
