package controller.commands;

import java.io.IOException;


import javax.servlet.ServletException;


public class UnknownCommand extends AbstractCommand {

	@Override
	public void processGet() throws ServletException, IOException {
		sc.getRequestDispatcher("/unknownCommand.jsp").forward(request, response);		
	}

	@Override
	public void processPost() throws ServletException, IOException {
		sc.getRequestDispatcher("/unknownCommand.jsp").forward(request, response);	
	}

}
