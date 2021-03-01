package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;
import service.UserService;
import service.UserServiceImpl;
import util.MailUtils;
import util.UUIDUtils;

/**
 * Servlet implementation class SendEmailServlet
 */
@WebServlet("/ResetPwdServlet")
public class ResetPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPwdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String username=request.getParameter("username");
		System.out.println(username);
		String password=request.getParameter("password");
		System.out.println(password);
		String email=request.getParameter("email");
		System.out.println(email);
		String codeinput=request.getParameter("code");
		UserService findemail=new UserServiceImpl();
		String eString=findemail.findByEmail(username);
		System.out.println(eString);
		Object sessionCode =  request.getSession().getAttribute("code");
		UserDao userDao=new UserDaoImpl();
		boolean h=userDao.findbyUser(username);
		System.out.println(sessionCode);
		if(h) {
			if(sessionCode==null) {
				request.setAttribute("ContextCode", "请先发送验证码");
				request.getRequestDispatcher("/ResetPassword.jsp").forward(request, response);
			

		}else {
			if (sessionCode.equals(codeinput)) {
				User user=new User();
				
				
				user.setUsername(username);
				user.setPassword(password);	
				user.setEmail(email);
				
				UserService userService =new UserServiceImpl();
				
				userService.resetpwd(user);
				
				
				request.setAttribute("msg", "密码重置成功");
			    request.getRequestDispatcher("/msg.jsp").forward(request, response);
			}
			else {
				request.setAttribute("ContextCode", "验证码错误");
				request.getRequestDispatcher("/ResetPassword.jsp").forward(request, response);
			
		}}
		}else {
			request.setAttribute("ContextCode", "用户不存在");
			request.getRequestDispatcher("/ResetPassword.jsp").forward(request, response);
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
