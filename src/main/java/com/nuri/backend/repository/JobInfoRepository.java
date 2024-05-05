package com.nuri.backend.repository;

import com.nuri.backend.domain.JobInfo;

import java.util.List;

import com.nuri.backend.dto.JobInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobInfoRepository extends JpaRepository<JobInfo, String> {
    Page<JobInfo> findAll(Pageable pageable);

    @Query(value = "SELECT j FROM JobInfo j WHERE distance(j.geoLocation.point, point(:x, :y)) < :distance")
    List<JobInfo> findJobsWithinDistance(@Param("x") double x, @Param("y") double y, @Param("distance") double distance);
}
