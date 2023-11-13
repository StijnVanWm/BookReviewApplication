package com.springboot.bookreview.services.impl;

import com.springboot.bookreview.dto.reviewDtos.ReviewAddDto;
import com.springboot.bookreview.dto.reviewDtos.ReviewDto;
import com.springboot.bookreview.dto.userDtos.UserDto;
import com.springboot.bookreview.entities.Book;
import com.springboot.bookreview.entities.Review;
import com.springboot.bookreview.entities.User;
import com.springboot.bookreview.exceptions.ResourceNotFoundException;
import com.springboot.bookreview.repositories.BookRepository;
import com.springboot.bookreview.repositories.ReviewRepository;
import com.springboot.bookreview.repositories.UserRepository;
import com.springboot.bookreview.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

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
    public List<ReviewDto> getReviewsByBookId(Long bookId) {
        return reviewRepository.getReviewsByBookId(bookId)
                .stream()
                .map(review -> mapEntityToDto(review))
                .collect(Collectors.toList());
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
