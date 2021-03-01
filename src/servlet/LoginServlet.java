package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;
import service.UserService;
import service.UserServiceImpl;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService loginService = new UserServiceImpl();
	private String url;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("servlet"+username);
		System.out.println("servlet"+password);
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		User returnUser = loginService.loginUser(user);
		System.out.println("servlet" + returnUser);
		if (returnUser.getUsername() != null&&returnUser.getPassword()!= null) {
			url = "/index.jsp";
			request.getSession().setAttribute("returnUser", returnUser);
		} else {
			url = "/login.jsp";
			request.setAttribute("Context", "’À∫≈ªÚ’ﬂ√‹¬Î¥ÌŒÛ");
		}
		request.getRequestDispatcher(url).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
