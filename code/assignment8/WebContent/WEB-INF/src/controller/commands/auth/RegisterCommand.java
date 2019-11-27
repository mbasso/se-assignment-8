package controller.commands.auth;

import auth.UserManager;
import controller.commands.AbstractCommand;
import exception.ExistingUserException;
import model.User;
import model.User.Role;

import java.io.IOException;

public class RegisterCommand extends AbstractCommand {

	@Override
	public void processGet() throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void processPost() throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
	
		try {
			UserManager.getShared().createUser(new User(username, password, name, email, city, country, Role.READER));
		} catch (ExistingUserException e) {
			throw new RuntimeException(e);
		}
		
		response.sendRedirect("/assignment8/");		
	}
}
