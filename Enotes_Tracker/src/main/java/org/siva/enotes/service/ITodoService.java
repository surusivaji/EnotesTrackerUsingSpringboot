package org.siva.enotes.service;

import org.siva.enotes.model.Todo;
import org.siva.enotes.model.User;
import org.springframework.data.domain.Page;

public interface ITodoService {
	Todo addTodo(Todo todo);
	Page<Todo> getAllTodosByUser(User user, int pageNo);
	Todo getTodoById(int id);
	void removeTodoById(int id);
	boolean removeTodosByUser(User user);
}
