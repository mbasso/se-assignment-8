package assignment8.web.view;

import assignment8.model.User;

public class AuthHelper {

	private User user;
	
	public AuthHelper(User user) {
		this.user = user;
	}
	
	public boolean isAuthenticated() {
		return (this.user != null);
	}
	
	public String getUsername() {		
		return (this.isAuthenticated()) ? this.user.getUsername() : null;
	}
}
