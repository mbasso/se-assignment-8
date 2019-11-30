package assignment8.web.command;

import assignment8.model.Address;
import assignment8.model.User;
import assignment8.web.annotation.Authorize;
import assignment8.web.view.SearchHelper;
import assignment8.web.view.UserHelper;

import java.io.IOException;
import javax.servlet.ServletException;

@Authorize
public class SearchCommand extends AbstractCommand {

	@Override
	public void processGet() throws IOException, ServletException {
		request.setAttribute("userHelper", new SearchHelper());
		request.setAttribute("userHelper", new UserHelper());
		sc.getRequestDispatcher("/user-search.jsp").forward(request, response);
	}

	@Override
	public void processPost() throws IOException, ServletException {
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		Address address = new Address(
			request.getParameter("country"),
			request.getParameter("city"),
			request.getParameter("street")
		);
		User bestFriend = userRepository.get(request.getParameter("bestfriend"));

		request.setAttribute("userHelper", new UserHelper());
		request.setAttribute("searchHelper", new SearchHelper(
				new User(username, null, name, bestFriend, address)
		));
		sc.getRequestDispatcher("/user-search.jsp").forward(request, response);
	}
}
