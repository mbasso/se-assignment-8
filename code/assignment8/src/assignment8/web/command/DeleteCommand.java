package assignment8.web.command;

import assignment8.web.annotation.Authorize;

import java.io.IOException;
import javax.servlet.ServletException;

@Authorize
public class DeleteCommand extends AbstractCommand {

	@Override
	public void processGet() throws IOException, ServletException {
		String username = request.getParameter("username");
		userRepository.deleteUser(username);
		response.sendRedirect("list");
	}

	@Override
	public void processPost() throws IOException {
		throw new UnsupportedOperationException();	
	}
}
