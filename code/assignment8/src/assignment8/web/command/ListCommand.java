package assignment8.web.command;

import assignment8.repository.UserRepository;
import assignment8.model.User;
import assignment8.web.annotation.Authorize;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

@Authorize
public class ListCommand extends AbstractCommand {

	@Override
	public void processGet() throws IOException, ServletException {
		List<User> users = new UserRepository().getAllUser();
		request.setAttribute("users", users);
		sc.getRequestDispatcher("/user-list.jsp").forward(request, response);
	}

	@Override
	public void processPost() throws IOException {
		throw new UnsupportedOperationException();	
	}
}
