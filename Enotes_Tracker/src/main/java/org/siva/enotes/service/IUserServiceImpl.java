package org.siva.enotes.service;

import java.util.Optional;

import org.siva.enotes.model.User;
import org.siva.enotes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

@Service
public class IUserServiceImpl implements IUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User addUser(User user) {
		User save = userRepository.save(user);
		if (save!=null) {
			return save;
		}
		else {
			return null;
		}
	}

	@Override
	public boolean emailIsPresent(String email) {
		Boolean existsByEmail = userRepository.existsByEmail(email);
		if (existsByEmail) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean mobileIsPresent(String mobile) {
		boolean present = userRepository.existsByMobile(mobile);
		if (present) {
			return true;
		}
		return false;
	}
	
	@Override
	public User login(String email, String password) {
		User user = userRepository.findByEmailAndPassword(email, password);
		return user;
	}
	
	@Override
	public User findById(int id) {
		Optional<User> optional = userRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		else {	
			return null;
		}
	}
	
	@Override
	public boolean deleteUser(User user) {
		try {
			userRepository.delete(user);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public User getByEmailAndMobile(String email, String mobile) {
		try {
			User user = userRepository.findByEmailAndMobile(email, mobile);
			if (user!=null) {
				return user;
			}
			else {
				return null;
			}
		}
		catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public User getUserByEmail(String email) {
		try {
			User user = userRepository.findByEmail(email);
			if (user!=null) {
				return user;
			}
			else {
				return null;
			}
		}
		catch (Exception e) {
			return null;
		}
	}
	
	public void removeSessionMessage() {
		HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		session.removeAttribute("msg");
		session.removeAttribute("logout");
	}
	

}
