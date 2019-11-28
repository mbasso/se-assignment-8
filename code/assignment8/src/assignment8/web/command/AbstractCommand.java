package assignment8.web.command;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import assignment8.repository.UserRepository;

public abstract class AbstractCommand {

	protected ServletContext sc;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected UserRepository userRepository;
	
	public void init(ServletContext sc, HttpServletRequest request, HttpServletResponse response){
		this.sc = sc;
		this.request = request;
		this.response = response;
		this.userRepository = new UserRepository();
	}
	
	public abstract void processGet() throws ServletException, IOException;
	public abstract void processPost() throws ServletException, IOException;
}
