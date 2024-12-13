package org.siva.enotes.service;

import java.util.List;
import java.util.Optional;

import org.siva.enotes.model.Todo;
import org.siva.enotes.model.User;
import org.siva.enotes.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ITodoServiceImpl implements ITodoService {
	
	@Autowired
	private TodoRepository todoRepository;
	
	@Override
	public Todo addTodo(Todo todo) {
		try {
			Todo save = todoRepository.save(todo);
			if (save!=null) {
				return save;
			}
			else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public Page<Todo> getAllTodosByUser(User user, int pageNo) {
		try {
			Pageable pageable = PageRequest.of(pageNo, 2);
			Page<Todo> pages = todoRepository.findByUser(user, pageable);
			return pages;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public Todo getTodoById(int id) {
		try {
			Optional<Todo> todo = todoRepository.findById(id);
			if (todo.isPresent()) {
				return todo.get();
			} 
			else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public void removeTodoById(int id) {
		try {
			todoRepository.deleteById(id);
		}
		catch (Exception e) {
			
		}
	}
	
	@Override
	public boolean removeTodosByUser(User user) {
		try {
			List<Todo> todos = todoRepository.findByUser(user);
			if (todos!=null) {
				todoRepository.deleteAllInBatch(todos);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

}
