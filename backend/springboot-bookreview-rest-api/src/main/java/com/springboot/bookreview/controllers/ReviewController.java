package com.springboot.bookreview.controllers;

import com.springboot.bookreview.dto.reviewDtos.ReviewAddDto;
import com.springboot.bookreview.dto.reviewDtos.ReviewDto;
import com.springboot.bookreview.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/books")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    //GET: api/books/1/reviews
    @GetMapping("{id}/reviews")
    public ResponseEntity<List<ReviewDto>> getAllBookReviews(@PathVariable(name = "id") Long bookId) {
        return ResponseEntity.ok(reviewService.getReviewsByBookId(bookId));
    }

    @PostMapping("{id}/reviews")
    public ResponseEntity<ReviewDto> addReviewToBook(@PathVariable(name = "id") Long bookId,
                                                     @RequestBody ReviewAddDto reviewAddDto) {
        return new ResponseEntity<>(reviewService.addReviewToBook(bookId, reviewAddDto), HttpStatus.CREATED);
    }
}
