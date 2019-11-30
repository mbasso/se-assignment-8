package assignment8.web.view;

import java.util.List;

import assignment8.model.User;
import assignment8.repository.UserRepository;

public class SearchHelper {
	private UserRepository userRepository;
	private User user;
	private List<User> users;

	public SearchHelper() {}

	public SearchHelper(User user) {
		this.user = user;
		this.userRepository = new UserRepository();
		this.users = userRepository.findUsers(user);
	}

	public User getUser() {
		return user;
	}

	public List<User> getUsers() {
		return users;
	}

}
