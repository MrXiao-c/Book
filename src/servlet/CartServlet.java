package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.booksDAO;

import entity.Carts;
import entity.books;
import service.CartService;
import service.OrderService;


/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String action;  
	private booksDAO itemsDAO=new booksDAO();; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
    	 super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter outPrintWriter=response.getWriter();
		if(request.getParameter("action")!=null) {
			this.action=request.getParameter("action");
			if(action.equals("add")) {
				if(addgoods(request,response)) {
					request.getRequestDispatcher("/Success.jsp").forward(request, response);
					
				}else {
					request.getRequestDispatcher("/fail.jsp").forward(request, response);
					
				}
			}
			if(action.equals("show"))//如果是显示购物车
			{
				request.getRequestDispatcher("/cart.jsp").forward(request, response);
				
			}
			if(action.equals("delete")) //如果是执行删除购物车中的商品
			{
				if(deletegoods(request, response))
				{
					request.getRequestDispatcher("/cart.jsp").forward(request, response);
					
				}
				else
				{
					request.getRequestDispatcher("/cart.jsp").forward(request, response);
					
				}
			}
			if(action.equals("buy")) {
				if(buygoods(request,response)) {
					request.getRequestDispatcher("/buySuccess.jsp").forward(request, response);
					
				}else {
					request.getRequestDispatcher("/fail.jsp").forward(request, response);
					
				}
			}
			if(action.equals("deleteorder")) {
				if(deleteOrder(request,response)) {
					request.getRequestDispatcher("/Order.jsp").forward(request, response);
					
				}else {
					request.getRequestDispatcher("/Order.jsp").forward(request, response);
					
				}
			}
			
		}
		
	}
private boolean addgoods(HttpServletRequest request, HttpServletResponse response) {
	
	int id = Integer.parseInt(request.getParameter("id"));
	       //商品数量
	       int number = Integer.parseInt(request.getParameter("num"));
	       //商品资料
	        books item = itemsDAO.getItemsById(id);//Integer.parseInt(id)转换成整型
	       System.out.println("商品资料"+item);
	       CartService cart=new CartService();
	     
	       int totalprice=item.getPrice()*number;
	       if(cart.findgoods(id).isEmpty()) {
	    	   cart.addgoods(id,item.getName(), item.getPrice(),number,totalprice,item.getPicture());
	       }
	       else if(!cart.findgoods(id).isEmpty()) {
	    	   cart.updategoods(id, number);
	       }
	       
	      
	         return true;
	      
	         
}

private boolean deletegoods(HttpServletRequest request, HttpServletResponse response)
{
	String id = request.getParameter("id");
	System.out.println(id);
	CartService cartService=new CartService();
	cartService.deletegoods(Integer.parseInt(id));
    if(cartService.deletegoods(Integer.parseInt(id)))
    {
    	return true;
    }
    else
    {
    	return false;
    }
}
private boolean buygoods(HttpServletRequest request, HttpServletResponse response) {
	OrderService orderService=new OrderService();
	String bookid = request.getParameter("id");
       //商品数量
       String buynum = request.getParameter("num");
       booksDAO booksDAO=new booksDAO();
       
       books books=new books();
       books=booksDAO.getItemsById(Integer.parseInt(bookid));
       String name = books.getName();
       int price=books.getPrice();
       String picture=books.getPicture();
       
       int id=1;
	orderService.addOrder(Integer.parseInt(bookid), name, price, Integer.parseInt(buynum), picture);
	return true;
	
}

private boolean deleteOrder(HttpServletRequest request, HttpServletResponse response) {
	OrderService orderService=new OrderService();
	String id = request.getParameter("id");
	orderService.deleteorder(Integer.parseInt(id));
	return true;
	
}

}
