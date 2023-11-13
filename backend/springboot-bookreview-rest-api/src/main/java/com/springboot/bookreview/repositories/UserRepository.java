package com.springboot.bookreview.repositories;

import com.springboot.bookreview.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
