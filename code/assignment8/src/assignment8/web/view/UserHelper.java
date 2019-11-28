package assignment8.web.view;

import java.util.List;

import assignment8.model.User;
import assignment8.repository.UserRepository;

public class UserHelper {
	private UserRepository userRepository;
	private User user;
	private List<User> users;
	
	public UserHelper() {
		this.userRepository = new UserRepository();
		this.users = userRepository.getAll();
	}
	
	public UserHelper(String username) {
		this();
		this.user = userRepository.get(username);
	}
	
	public User getUser() {
		return user;
	}
	
	public List<User> getUsers() {
		return users;
	}

}
