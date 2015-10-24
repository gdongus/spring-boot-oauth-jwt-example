package gd.example.oauth;

import gd.example.oauth.domain.User;
import gd.example.oauth.domain.UserCredentials;
import gd.example.oauth.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Component to add convenient methods for development. E.g. creates some users to start with. 
 */
@Component
@Profile(Profiles.DEV)
@Slf4j
public class Dev {
	@Autowired
	private UserRepository userRepository;

	/**
	 * Creates default users to start with. These user are generated automatically when starting with "DEV" profile.
	 */
	@PostConstruct
	void createDefaultUsers() {
		log.info("Creating default users for DEV environment");

		User user = new User();
		user.setEmail("bob@example.com");
		UserCredentials credentials = new UserCredentials();
		credentials.setUsername("bob");
		credentials.setPasswordHash("$2a$08$JPRSocoRc2yfGWfP1EX/G.FU/nl/1A7CpRWppJVp3jMJd6SNqgXq6");
		credentials.setUser(user);
		user.setCredentials(credentials);
		userRepository.save(user);
	}
}