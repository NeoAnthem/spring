package com.example.student.dto;

import lombok.AllArgsConstructor;
import com.example.student.entity.StatusReview;
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
