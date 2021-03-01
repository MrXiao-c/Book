package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.booksDAO;
import entity.Order;
import entity.books;
import service.OrderService;
import util.Pager;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String action;     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		//接收request里的参数
		String orderName = request.getParameter("orderName");
		System.out.println("servlet"+orderName);
		
		
		
		
		//校验pageNum参数输入的合法性
		String pageNumStr = request.getParameter("pageNum");
		
		
		int pageNum = 1;
		
		if(pageNumStr!=null&&!"".equals(pageNumStr.trim())){
			pageNum = Integer.parseInt(pageNumStr);
		}
		
		int pageSize = 4;
		String pageSizeStr = request.getParameter("pageSize");
		if(pageSizeStr!=null&&!"".equals(pageSizeStr.trim())){
			pageSize = Integer.parseInt(pageSizeStr);
		}
		//组装查询条件
		Order searchModel = new Order();
		searchModel.setName(orderName);
		
		//调用service获取查询结果
		OrderService OrderService=new OrderService();
		Pager<Order> result = OrderService.getAllOrders(searchModel, pageNum, pageSize);
		System.out.println("总数"+result.getTotalPage());
		//返回结果到页面
		request.setAttribute("result", result);
		request.setAttribute("orderName", orderName);
		
		request.getRequestDispatcher("Order.jsp").forward(request, response);
		
	
	
}
}
