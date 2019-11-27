package controller.commands.auth;

import auth.AuthManager;
import controller.commands.AbstractCommand;
import java.io.IOException;

public class LoginCommand extends AbstractCommand {

	@Override
	public void processGet() throws IOException {
		throw new UnsupportedOperationException();
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
