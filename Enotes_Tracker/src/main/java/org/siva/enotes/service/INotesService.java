package org.siva.enotes.service;


import org.siva.enotes.model.Notes;
import org.siva.enotes.model.User;
import org.springframework.data.domain.Page;

public interface INotesService {
	Notes saveNotes(Notes notes);
	//List<Notes> findAllNotes(User user);
	Page<Notes> findAllNotes(User user, int pageNo);
	Notes findByNotes(int id);
	void removeNotes(int id);
	boolean removeNotesByUser(User user);
}
