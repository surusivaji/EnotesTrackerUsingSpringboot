package org.siva.enotes.service;

import org.siva.enotes.model.User;

public interface IUserService {
	boolean emailIsPresent(String email);
	boolean mobileIsPresent(String mobile);
	User addUser(User user);
	User login(String email, String password);
	User findById(int id);
	boolean deleteUser(User user);
	User getByEmailAndMobile(String email, String mobile);
	User getUserByEmail(String email);
}
