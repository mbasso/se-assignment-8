package assignment8.web.command;

import assignment8.model.User;
import assignment8.web.annotation.Authorize;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

@Authorize
public class EditCommand extends AbstractCommand {

	@Override
	public void processGet() throws IOException, ServletException {
		String username = request.getParameter("username");
		User existingUser = userRepository.getUser(username);
		request.setAttribute("user", existingUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/user-form.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	public void processPost() throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		User newUser = new User(username, password, name, null, null);
		userRepository.updateUser(newUser);
		response.sendRedirect("list");
	}
}
