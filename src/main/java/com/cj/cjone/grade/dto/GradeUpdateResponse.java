package com.cj.cjone.grade.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GradeUpdateResponse {
    private Long userId;
    private Long amount;
    private String gradeName;
}