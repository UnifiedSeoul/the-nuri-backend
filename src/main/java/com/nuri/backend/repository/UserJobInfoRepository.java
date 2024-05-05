package com.nuri.backend.repository;

import com.nuri.backend.entity.UserJobInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserJobInfoRepository extends JpaRepository<UserJobInfo, Long> {
    List<UserJobInfo> findAllByUserId(Long userId);
}
