package gd.example.oauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gd.example.oauth.repository.UserRepository;

/**
 * Service class to access a user. 
 */
@Service
public class UserService {
	@SuppressWarnings("unused")
	@Autowired
	private UserRepository userRepository;
}