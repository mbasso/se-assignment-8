package assignment8.web.command;

import assignment8.web.annotation.Authorize;
import assignment8.web.view.UserHelper;

import java.io.IOException;
import javax.servlet.ServletException;

@Authorize
public class ListCommand extends AbstractCommand {

	@Override
	public void processGet() throws IOException, ServletException {
		request.setAttribute("userHelper", new UserHelper());
		sc.getRequestDispatcher("/user-list.jsp").forward(request, response);
	}

	@Override
	public void processPost() throws IOException {
		throw new UnsupportedOperationException();	
	}
}
