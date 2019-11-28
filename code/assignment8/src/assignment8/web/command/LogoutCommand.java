package assignment8.web.command;

import java.io.IOException;

import javax.servlet.ServletException;

import assignment8.web.auth.AuthManager;

public class LogoutCommand extends AbstractCommand {
	
	@Override
	public void processGet() throws ServletException, IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void processPost() throws ServletException, IOException {
		AuthManager auth = new AuthManager(request.getSession());
		
		auth.logout();
		
		response.sendRedirect("/");
	}

}
