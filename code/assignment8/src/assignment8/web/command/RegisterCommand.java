package assignment8.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import assignment8.model.Address;
import assignment8.model.User;
import assignment8.web.view.UserHelper;

public class RegisterCommand extends AbstractCommand {

	@Override
	public void processGet() throws IOException, ServletException {
		request.setAttribute("userHelper", new UserHelper());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
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
		User bestFriend = userRepository.get(request.getParameter("bestfriend"));
		User newUser = new User(username, password, name, bestFriend, address);
		userRepository.insert(newUser);
		response.sendRedirect("/assignment8/login");	
	}
}
