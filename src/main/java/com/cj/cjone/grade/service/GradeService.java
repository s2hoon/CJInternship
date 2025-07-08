package com.cj.cjone.grade.service;

import com.cj.cjone.grade.entity.Grade;
import com.cj.cjone.grade.repository.GradeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;


    @Transactional
    public Grade updateGradeByAmount(Long userId, Long amount) {
        Grade grade = gradeRepository.findByUserId(userId)
                .orElseGet(() -> Grade.builder()
                        .userId(userId)
                        .amount(0L)
                        .gradeName("기본")
                        .build());

        grade.setAmount(amount); // 현재 누적 금액 설정
        grade.setGradeName(determineGradeName(amount)); // 금액에 따른 등급 설정

        gradeRepository.save(grade); // 저장 or 갱신
        return grade;
    }

    private String determineGradeName(Long amount) {
        if (amount >= 3_000_000) {
            return "SVIP";
        } else if (amount >= 2_000_000) {
            return "VVIP";
        } else if (amount >= 900_000) {
            return "VIP";
        } else {
            return "기본";
        }
    }
}


