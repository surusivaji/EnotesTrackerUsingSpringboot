package org.siva.enotes.repository;

import java.util.List;

import org.siva.enotes.model.Notes;
import org.siva.enotes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepository extends JpaRepository<Notes, Integer> { 
	List<Notes> findByUser(User user);
}
