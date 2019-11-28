package assignment8.web.auth;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import assignment8.model.User;
import assignment8.repository.UserRepository;

public class AuthManager {

	private static String LOGGED_USER_KEY = "loggedUser";

	private HttpSession session;
	
	public AuthManager(HttpSession session) {
		this.session = session;
	}
	
	public User getUser() {
		return (User) session.getAttribute(LOGGED_USER_KEY);
	}
	
	public boolean login(String username, String password) {
		Optional<User> selectedUser = new UserRepository()
											.getAllUser()
											.stream()
											.filter(user ->
												user.getUsername().equals(username) &&
												user.getPassword().equals(password)
											)
											.findFirst();
		
		if (!selectedUser.isPresent()) {
			return false;
		}

		session.setAttribute(LOGGED_USER_KEY, selectedUser.get());
		return true;
	}
	
	public void logout() {
		session.removeAttribute(LOGGED_USER_KEY);
	}

}
