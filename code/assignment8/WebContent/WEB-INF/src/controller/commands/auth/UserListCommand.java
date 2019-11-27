package controller.commands.auth;

import auth.UserManager;
import controller.commands.AbstractCommand;
import model.auth.User;
import model.auth.User.Role;
import annotation.Authorize;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

@Authorize(roles = {Role.ADMIN})
public class UserListCommand extends AbstractCommand {

	@Override
	public void processGet() throws IOException, ServletException {
		List<User> users = UserManager
				.getShared()
				.getUsers();
		
		request.setAttribute("users", users);
		
		sc.getRequestDispatcher("/users/index.jsp").forward(request, response);
	}

	@Override
	public void processPost() throws IOException {
		throw new UnsupportedOperationException();	
	}
}
