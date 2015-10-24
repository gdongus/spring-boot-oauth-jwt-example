package gd.example.oauth.repository;

import gd.example.oauth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Access to the user data. JpaRepository grants us convenient access methods here.
 */
public interface UserRepository extends JpaRepository<User, Long> {
	/**
	 * Find a user by email
	 *
	 * @param email the user's email
	 * @return Optional<User> returns an {@link Optional} which contains the user with the given email address or null.
	 */
	Optional<User> findOneByEmail(String email);

	/**
	 * Find a user by ID
	 *
	 * @param id the user's ID
	 * @return User returns an {@link User} which contains the user or null.
	 */
	User findById(Long id);
}