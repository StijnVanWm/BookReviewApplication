package com.springboot.bookreview.repositories;

import com.springboot.bookreview.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> getReviewsByBookId(Long bookId);
}
