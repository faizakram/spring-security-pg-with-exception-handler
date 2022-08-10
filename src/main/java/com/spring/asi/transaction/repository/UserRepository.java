package com.spring.asi.transaction.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.asi.transaction.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);
	
	@Query("from User u where u.email =?1 and u.resetPassKey=?2")
    Optional<User> findByEmailAndSecretKey(String email, String secretKey);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
