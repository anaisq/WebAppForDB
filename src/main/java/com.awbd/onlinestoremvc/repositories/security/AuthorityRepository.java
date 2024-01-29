package com.awbd.onlinestoremvc.repositories.security;

import com.awbd.onlinestoremvc.model.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
