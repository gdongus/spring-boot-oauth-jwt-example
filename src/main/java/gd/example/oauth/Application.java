package gd.example.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class which bootstraps the spring application.
 */
@SpringBootApplication
public class Application {

	/**
	 * Main application entry. Bootstraps the spring container.
	 * 
	 * @param args
	 *            possible command line args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
