package com.nuri.backend.repository;

import com.nuri.backend.domain.JobInfo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JobInfoRepository extends JpaRepository<JobInfo, String> {

    @Query(value = "SELECT j FROM JobInfo j WHERE distance(j.geoLocation.point, point(:x, :y)) < :distance")
    List<JobInfo> findJobsWithinDistance(@Param("x") double x, @Param("y") double y, @Param("distance") double distance);
}
