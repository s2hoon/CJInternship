package com.cj.cjone.grade.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GradeUpdateRequest {
    private Long userId;
    private Long amount;
}