package gd.example.oauth.repository;

import gd.example.oauth.domain.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Service to access a users credential data. JpaRepository grants us convenient access methods here.
 */
public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Long> {

	/**
	 * Find user credentials by username.
	 *
	 * @param username name of the user whose credentials should be returned. Must not be null nor empty.
	 * @return UserCredentials credentials of the user with the given name or null if none could be found. 
	 */
	public UserCredentials getByUsername(String username);
}