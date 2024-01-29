package com.awbd.onlinestoremvc.repositories.security;

import com.awbd.onlinestoremvc.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
}
