package controller.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.AuthManager;
import controller.FrontController;
import model.auth.User;
import view.auth.AuthHelper;
import annotation.Authorize;

@WebFilter("/*")
public class AuthFilter implements Filter {

    private static Set<String> excluded;

	public void init(FilterConfig filterConfig) throws ServletException {
		String excludedString = filterConfig.getInitParameter("excludePublicRoutes");
        if (excludedString != null) {
            excluded = Collections.unmodifiableSet(
                new HashSet<>(Arrays.asList(excludedString.split(" ", 0))));
        } else {
            excluded = Collections.<String>emptySet();
        }
	}

    boolean isExcluded(HttpServletRequest request) {
        String path = request.getRequestURI();
        path = path + (path.endsWith("/") ? "" : '/');
        
        String extension = path.substring(path.lastIndexOf('/')).toLowerCase();
        return excluded.contains(extension);
    }
	
    public AuthFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        AuthManager auth = new AuthManager(httpRequest.getSession());
        User currentUser = auth.getUser();
        
        request.setAttribute("authHelper", new AuthHelper(currentUser));
        
        // get command class and check auth annotation
        Class<?> commandClass = FrontController.getCommandClass(httpRequest);
        Authorize authorizeAnnotation = commandClass.getAnnotation(Authorize.class);
        
        // redirect to login if user is not authorized
        if (
        	authorizeAnnotation == null ||
        	currentUser != null &&
        	Arrays.asList(authorizeAnnotation.roles()).contains(currentUser.getRole())
        ) {
            chain.doFilter(request, response);
        } else {
        	httpResponse.sendRedirect("/assignment8");
        	return;
        }
	}

}
