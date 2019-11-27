package view.auth;

import model.User;
import model.User.Role;

public class AuthHelper {

	private User user;
	
	public AuthHelper(User user) {
		this.user = user;
	}
	
	public boolean isAuthenticated() {
		return (this.user != null);
	}
	
	public boolean isAdmin() {	
		return (this.isAuthenticated()) && (this.user.getRole() == Role.ADMIN);
	}
	
	public String getUsername() {		
		return (this.isAuthenticated()) ? this.user.getUsername() : null;
	}
}
