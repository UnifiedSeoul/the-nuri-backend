package com.nuri.backend.repository;

import com.nuri.backend.domain.JobInfo;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JobInfoRepository extends JpaRepository<JobInfo, String> {
    Page<JobInfo> findAll(Pageable pageable);

    @Query(value = "SELECT j FROM JobInfo j WHERE distance(j.geoLocation.point, point(:x, :y)) < :distance")
    List<JobInfo> findJobsWithinDistance(@Param("x") double x, @Param("y") double y, @Param("distance") double distance);

    @Modifying
    @Query("UPDATE JobInfo j SET j.viewCount = j.viewCount + 1 WHERE j.jobId = :jobId")
    void incrementViewCount(@Param("jobId") String jobId);

    @Modifying
    @Query("UPDATE JobInfo j SET j.homePageVisitCount = j.homePageVisitCount + 1 WHERE j.jobId = :jobId")
    void incrementHomePageViewCount(@Param("jobId") String jobId);
}
