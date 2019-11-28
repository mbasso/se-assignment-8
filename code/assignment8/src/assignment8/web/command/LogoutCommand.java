package assignment8.web.command;

import java.io.IOException;

import javax.servlet.ServletException;

import assignment8.web.auth.AuthManager;

public class LogoutCommand extends AbstractCommand {
	
	@Override
	public void processGet() throws ServletException, IOException {
		new AuthManager(request.getSession()).logout();
		response.sendRedirect("/assignment8");
	}

	@Override
	public void processPost() throws ServletException, IOException {
		throw new UnsupportedOperationException();
	}

}
