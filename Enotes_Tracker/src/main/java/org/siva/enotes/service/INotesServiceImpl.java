package org.siva.enotes.service;

import java.util.List;
import java.util.Optional;

import org.siva.enotes.model.Notes;
import org.siva.enotes.model.User;
import org.siva.enotes.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class INotesServiceImpl implements INotesService {

	@Autowired
	private NotesRepository notesRepository;
	
	@Override
	public Notes saveNotes(Notes notes) {
		Notes save = notesRepository.save(notes);
		return save;
	}
	
	@Override
	public List<Notes> findAllNotes(User user) {
		List<Notes> list = notesRepository.findByUser(user);
		return list;
	}
	
	@Override
	public Notes findByNotes(int id) {
		Optional<Notes> optional = notesRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	@Override
	public void removeNotes(int id) {
		notesRepository.deleteById(id);
	}
	
	@Override
	public boolean removeNotesByUser(User user) {
		try {
			List<Notes> notes = notesRepository.findByUser(user);
			try {
				notesRepository.deleteAllInBatch(notes);
				return false;
			}
			catch (Exception e) {
				return false;
			}
		}
		catch (Exception e) {
			return false;
		}
	}
	
}
