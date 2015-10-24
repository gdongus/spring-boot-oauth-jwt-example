package gd.example.oauth.service;

import gd.example.oauth.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gd.example.oauth.repository.UserRepository;

import java.util.List;

/**
 * Service class to access a user. 
 */
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public User getUser(Long id) {
		return userRepository.findById(id);
	}
}