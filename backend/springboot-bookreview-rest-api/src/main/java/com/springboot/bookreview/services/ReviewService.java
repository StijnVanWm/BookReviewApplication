package com.springboot.bookreview.services;

import com.springboot.bookreview.dto.reviewDtos.ReviewAddDto;
import com.springboot.bookreview.dto.reviewDtos.ReviewDto;

import java.util.List;

public interface ReviewService {

    List<ReviewDto> getReviewsByBookId(Long bookId);
    ReviewDto addReviewToBook(Long bookId, ReviewAddDto reviewAddDto);
    

}
