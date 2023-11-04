package com.springboot.bookreview.repositories;

import com.springboot.bookreview.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    //Implements all crud operations needed
}
