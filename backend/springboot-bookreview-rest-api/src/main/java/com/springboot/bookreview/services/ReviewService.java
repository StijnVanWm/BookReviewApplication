package com.springboot.bookreview.services;

import com.springboot.bookreview.dto.reviewDtos.ReviewAddDto;
import com.springboot.bookreview.dto.reviewDtos.ReviewDto;
import com.springboot.bookreview.dto.reviewDtos.ReviewResponse;

import java.util.List;

public interface ReviewService {

    ReviewResponse getReviewsByBookId(Long bookId, int pageNo, int pageSize, String sortBy);
    ReviewDto addReviewToBook(Long bookId, ReviewAddDto reviewAddDto);
    

}
