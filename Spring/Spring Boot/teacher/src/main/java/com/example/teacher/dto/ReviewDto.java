package com.example.teacher.dto;

import com.example.teacher.entity.StatusReview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private String review;
    private StatusReview status;
}
