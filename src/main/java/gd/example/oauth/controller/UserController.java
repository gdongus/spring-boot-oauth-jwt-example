package gd.example.oauth.controller;

import gd.example.oauth.domain.User;
import gd.example.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Basic REST endpoint to retrieve users
 */
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/users")
	@PreAuthorize("isAuthenticated()")
	public List<User> getUsers() {
		return userService.getUsers();
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public User getUser(@RequestParam(value = "id", required = true) Long id) {
		return userService.getUser(id);
	}
}
