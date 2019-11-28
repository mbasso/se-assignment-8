package assignment8.web.auth;

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
		User selectedUser = new UserRepository().get(username);
		
		if (selectedUser == null || !selectedUser.getPassword().equals(password)) {
			return false;
		}

		session.setAttribute(LOGGED_USER_KEY, selectedUser);
		return true;
	}
	
	public void logout() {
		session.removeAttribute(LOGGED_USER_KEY);
	}

}
