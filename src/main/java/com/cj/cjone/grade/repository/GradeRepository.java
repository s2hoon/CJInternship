package com.cj.cjone.grade.repository;

import com.cj.cjone.grade.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface GradeRepository extends JpaRepository<Grade,Long> {

    Optional<Grade> findByUserId(Long userId);
}
