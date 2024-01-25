package com.springboot.bookreview.services.impl;

import com.springboot.bookreview.dto.reviewDtos.ReviewAddDto;
import com.springboot.bookreview.dto.reviewDtos.ReviewDto;
import com.springboot.bookreview.dto.reviewDtos.ReviewResponse;
import com.springboot.bookreview.entities.Book;
import com.springboot.bookreview.entities.Review;
import com.springboot.bookreview.entities.User;
import com.springboot.bookreview.exceptions.ResourceNotFoundException;
import com.springboot.bookreview.repositories.BookRepository;
import com.springboot.bookreview.repositories.ReviewRepository;
import com.springboot.bookreview.repositories.UserRepository;
import com.springboot.bookreview.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             BookRepository bookRepository,
                             UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }
    @Override
    public ReviewResponse getReviewsByBookId(Long bookId, int pageNo, int pageSize, String sortBy) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());

        Page<Review> reviews = reviewRepository.getReviewsByBookId(bookId, pageable);

        List<Review> reviewsList = reviews.getContent();


        List<ReviewDto> content = reviewsList
                                    .stream()
                                    .map(this::mapEntityToDto)
                                    .collect(Collectors.toList());


        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.setContent(content);
        reviewResponse.setPageNo(reviews.getNumber());
        reviewResponse.setPageSize(reviews.getSize());
        reviewResponse.setTotalElements(reviews.getTotalElements());
        reviewResponse.setTotalPages(reviews.getTotalPages());
        reviewResponse.setLastPage(reviews.isLast());

        return reviewResponse;

    }

    @Override
    public ReviewDto addReviewToBook(Long bookId, ReviewAddDto reviewAddDto) {

        User userFromDb = userRepository.findById(reviewAddDto.getUserId()).orElseThrow(()
                -> new ResourceNotFoundException("User", "id", reviewAddDto.getUserId()));
        Book bookFromDb = bookRepository.findById(bookId).orElseThrow(()
                -> new ResourceNotFoundException("Book", "id", bookId));

        Review reviewToAdd = mapAddDtoToEntity(reviewAddDto);
        reviewToAdd.setBook(bookFromDb);
        reviewToAdd.setUser(userFromDb);

        Review addedReview = reviewRepository.save(reviewToAdd);

        return mapEntityToDto(addedReview);
    }


    private ReviewDto mapEntityToDto(Review review) {

        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setDescription(review.getDescription());
        reviewDto.setCreationDateTime(review.getCreationDateTime());
        reviewDto.setLikes(review.getLikes());
        reviewDto.setDislikes(review.getDislikes());
        reviewDto.setScore(review.getScore());
        reviewDto.setUser(UserServiceImpl.mapEntityToDto(review.getUser()));

        return reviewDto;
    }

    private Review mapAddDtoToEntity(ReviewAddDto reviewAddDto) {
        return new Review(
                reviewAddDto.getScore(),
                reviewAddDto.getDescription()
        );
    }

}
