package org.siva.enotes.service;

import java.util.List;

import org.siva.enotes.model.Notes;
import org.siva.enotes.model.User;

public interface INotesService {
	Notes saveNotes(Notes notes);
	List<Notes> findAllNotes(User user);
	Notes findByNotes(int id);
	void removeNotes(int id);
	boolean removeNotesByUser(User user);
}
