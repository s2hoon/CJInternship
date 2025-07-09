package com.cj.cjone.user;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public class BaseEntity {
    @CreatedDate
    private LocalDateTime createdDate; // 등록시간

    @LastModifiedDate
    private LocalDateTime updatedDate; // 수정시간

}
