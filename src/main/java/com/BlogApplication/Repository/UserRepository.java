package com.BlogApplication.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BlogApplication.Entity.User;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
	
	Optional<User> findByEmail(String email);

}
