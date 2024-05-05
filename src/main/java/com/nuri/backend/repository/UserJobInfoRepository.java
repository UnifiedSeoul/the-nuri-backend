package com.nuri.backend.repository;

import com.nuri.backend.entity.UserJobInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserJobInfoRepository extends JpaRepository<UserJobInfo, Long> {
    List<UserJobInfo> findAllById(Long id);
}
