package gd.example.oauth.domain;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Represents a user. Stores it's settings, profile information and references all information needed to authenticate a
 * user via {@link UserCredentials}.
 */
@Entity
@Table(name = "usr")
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@Getter(onMethod = @__(@JsonIgnore))
	private UserCredentials credentials;

	@Column(name = "email", nullable = false, unique = true)
	private String email;
}