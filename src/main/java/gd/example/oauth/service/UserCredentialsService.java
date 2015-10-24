package gd.example.oauth.service;

import gd.example.oauth.repository.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service to access a user's credentials.
 */
@Service
public class UserCredentialsService implements UserDetailsService {

	@Autowired
	private UserCredentialsRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.getByUsername(username);
	}
}