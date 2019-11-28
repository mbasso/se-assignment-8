package assignment8.web.command;

import assignment8.web.auth.AuthManager;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

public class LoginCommand extends AbstractCommand {

	@Override
	public void processGet() throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	public void processPost() throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		AuthManager auth = new AuthManager(request.getSession());
		
		if (!auth.login(username, password)) {
			// FIXME
			throw new RuntimeException("Login Failed");
		}
		
		response.sendRedirect("/assignment8/");
	}
}
