package assignment8.web.command;

import assignment8.model.Address;
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
		User existingUser = userRepository.get(username);
		request.setAttribute("user", existingUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/user-form.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	public void processPost() throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		Address address = new Address(
			request.getParameter("country"),
			request.getParameter("city"),
			request.getParameter("street")
		);
		User newUser = new User(username, password, name, null, address);
		System.out.println(username);
		System.out.println(password);
		System.out.println(name);
		System.out.println(newUser);
		userRepository.update(newUser);
		response.sendRedirect("/assignment8/list");
	}
}
