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
import util.MailUtils;
import util.UUIDUtils;

/**
 * Servlet implementation class ResetServlet
 */
@WebServlet("/SendmailServlet")
public class SendmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendmailServlet() {
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
		String rob=UUIDUtils.getRandom();
	
		
		if (email.equals(eString)) {
			try {
				request.getSession().setAttribute("code", rob);
				MailUtils.sendMail(email, rob);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
