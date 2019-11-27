package auth;

import exception.ExistingUserException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.auth.User;

public class UserManager {
	
	/** Singleton **/
	private UserManager() {}
	
	private static UserManager sharedManager = null;
	
	public static UserManager getShared() {
		if (sharedManager == null) {
			sharedManager = new UserManager();
		}
		
		return sharedManager;
	}
	
	private List<User> users = new ArrayList<User> (Arrays.<User>asList(
			new User ("mbasso", "mbassopassword", "Matteo Basso", "bassom@usi.ch", "Lugano", "Switzerland", User.Role.ADMIN),
			new User ("mferri", "123", "Marco Ferri", "ferrima@usi.ch", "Lugano", "Switzerland", User.Role.ADMIN),
			new User ("gadorni", "gadornipassword", "Giorgia Adorni", "adorng@usi.ch", "Lugano", "Switzerland", User.Role.ADMIN),
			new User ("ecereda", "eceredapassword", "Elia Cereda", "cerede@usi.ch", "Lugano", "Switzerland", User.Role.ADMIN),
			new User ("admin", "admin", "Admin", "admin@swe.it", "Lugano", "Switzerland", User.Role.ADMIN),
			new User ("reader", "reader", "Reader", "reader@swe.it", "Lugano", "Switzerland", User.Role.READER)
	));
	
	public void createUser(User user) throws ExistingUserException {
		if (users.stream().anyMatch(usr -> usr.equals(user))) {
			throw new ExistingUserException();
		}
		
		users.add(user);
	}
	
	public List<User> getUsers() {
		return users;
	}
}
