package com.example.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.User;

@Repository
public interface UserDetailRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String name);
}
