package org.siva.enotes.repository;

import org.siva.enotes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
	 Boolean existsByEmail(String email);
	 Boolean existsByMobile(String mobile);
	 User findByEmailAndPassword(String email, String password);
	 User findByEmailAndMobile(String email, String mobile);
	 User findByEmail(String email);
}
