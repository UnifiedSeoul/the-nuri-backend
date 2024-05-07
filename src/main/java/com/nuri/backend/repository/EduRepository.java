package com.nuri.backend.repository;

import com.nuri.backend.domain.Edu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EduRepository extends JpaRepository<Edu, String> {
    Page<Edu> findAll(Pageable pageable);
}
