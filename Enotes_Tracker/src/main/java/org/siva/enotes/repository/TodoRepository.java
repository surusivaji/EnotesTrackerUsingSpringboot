package org.siva.enotes.repository;

import java.util.List;

import org.siva.enotes.model.Todo;
import org.siva.enotes.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
	List<Todo> findByUser(User user);
	Page<Todo> findByUser(User user, Pageable pageable);
}
