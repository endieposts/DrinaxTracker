package com.endie.drinaxtracker.repository;

import com.endie.drinaxtracker.model.User;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Hidden
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}