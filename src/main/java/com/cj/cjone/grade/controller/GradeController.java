package com.cj.cjone.grade.controller;


import com.cj.cjone.grade.dto.GradeUpdateRequest;
import com.cj.cjone.grade.dto.GradeUpdateResponse;
import com.cj.cjone.grade.entity.Grade;
import com.cj.cjone.grade.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grade")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    @PostMapping("/update")
    public GradeUpdateResponse updateGrade(@RequestBody GradeUpdateRequest request) {
        Grade updatedGrade = gradeService.updateGradeByAmount(request.getUserId(), request.getAmount());
        return new GradeUpdateResponse(
                updatedGrade.getUserId(),
                updatedGrade.getAmount(),
                updatedGrade.getGradeName()
        );
    }

}
