package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	private String uri;

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;

		String uri = request.getRequestURI();
		String requestPath = uri.substring(uri.lastIndexOf("/") + 1, uri.length());
		if ("login.jsp".equals(requestPath) || "LoginServlet".equals(requestPath)
				||"register.jsp".equals(requestPath)||"RegistServlet".equals(requestPath)
				
				||"Check".equals(requestPath)	||"Check.jsp".equals(requestPath)||"ResetPassword.jsp".equals(requestPath)
				||"ResetPwdServlet".equals(requestPath)
				||"SendmailServlet".equals(requestPath)||uri.contains(".jpg")||uri.contains(".css")) {
			arg2.doFilter(request, response);
		} else {
			HttpSession httpSession = request.getSession(false);
			if (httpSession != null) {
				Object object = httpSession.getAttribute("returnUser");
				if (object != null) {
					if ("booksServlet".equals(requestPath)) {
						requestPath = "/index.jsp";
					}
					if ("cartsServlet".equals(requestPath)) {
						requestPath = "/cart.jsp";
					}
					
					arg2.doFilter(request, response);
				} else {
					requestPath = "/login.jsp";
				}
			} else {
				requestPath = "/login.jsp";
			}
			RequestDispatcher rd=request.getRequestDispatcher(requestPath);
			try {
				rd.forward(request, response);
			         return;
			}catch(Exception e){}
		
		}

	}

}
