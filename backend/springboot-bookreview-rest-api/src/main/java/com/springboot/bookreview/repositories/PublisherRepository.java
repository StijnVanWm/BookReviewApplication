package com.springboot.bookreview.repositories;

import com.springboot.bookreview.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
