package com.springboot.bookreview.repositories;

import com.springboot.bookreview.entities.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> getReviewsByBookId(Long bookId, Pageable pageable);
}
