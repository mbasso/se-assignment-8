package assignment8.web.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import assignment8.repository.UserRepository;
import assignment8.web.command.AbstractCommand;
import assignment8.web.command.UnknownCommand;

@WebServlet("/")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static Class<?> getCommandClass(HttpServletRequest request) {
		String action = request.getServletPath();

		//retrieve command name
		String commandName = action.replace("assignment8", "").replace("/", "");
		commandName = commandName.length() > 0
				? commandName.substring(0, 1).toUpperCase() + commandName.substring(1)
				: "List";
		commandName = "assignment8.web.command." + commandName + "Command";
		
		//retrieve command implementation
		//using reflection the front controller works for any
		//number of implemented commands 
		Class<?> commandClass;
		try {
			commandClass = Class.forName(commandName);
		} catch (ClassNotFoundException e) {
			commandClass = UnknownCommand.class;
		}
		
		return commandClass;
	}
       
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	private AbstractCommand createComand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Class<?> commandClass = FrontController.getCommandClass(request);
		
		//create command
		AbstractCommand command;
		try {
			command = (AbstractCommand) commandClass.newInstance();
		} catch (InstantiationException e) {
			getServletContext().getRequestDispatcher("/failToCreateCommand.jsp").forward(request, response);
			return null;
		} catch (IllegalAccessException e) {
			getServletContext().getRequestDispatcher("/failToCreateCommand.jsp").forward(request, response);
			return null;
		}
		return command;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AbstractCommand command = createComand(request, response);
		
		if (command == null) {
			return;
		}
		
		//run command
		command.init(getServletContext(), request, response);
		command.processGet();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		AbstractCommand command = createComand(request, response);
		
		if (command == null) {
			return;
		}
		
		//run command
		command.init(getServletContext(), request, response);
		command.processPost();
		
	}

}
