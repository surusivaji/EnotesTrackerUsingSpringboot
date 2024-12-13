package org.siva.enotes.service;

import java.util.List;
import java.util.Optional;

import org.siva.enotes.model.Notes;
import org.siva.enotes.model.User;
import org.siva.enotes.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	public Page<Notes> findAllNotes(User user, int pageNo) {
		try {
			Pageable pageable = PageRequest.of(pageNo, 2, Sort.by(Sort.Direction.DESC, "id"));
			Page<Notes> list = notesRepository.findByUser(user, pageable);
			return list;
		} catch (Exception e) {
			return null;
		}
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
