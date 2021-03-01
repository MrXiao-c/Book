package servlet;

import java.io.Writer;
import com.alibaba.fastjson.JSON;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.booksDAO;
import entity.books;
import util.Pager;

/**
 * Servlet implementation class ItemsServlet
 */
@WebServlet("/booksServlet")
public class booksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public booksServlet() {
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
		request.setCharacterEncoding("UTF-8");
		//接收request里的参数
		String bookName = request.getParameter("bookName");
		System.out.println("servlet"+bookName);
		
		
		
		
		//校验pageNum参数输入的合法性
		String pageNumStr = request.getParameter("pageNum");
		
		
		int pageNum = 1;
		
		if(pageNumStr!=null&&!"".equals(pageNumStr.trim())){
			pageNum = Integer.parseInt(pageNumStr);
		}
		
		int pageSize = 8;
		String pageSizeStr = request.getParameter("pageSize");
		if(pageSizeStr!=null&&!"".equals(pageSizeStr.trim())){
			pageSize = Integer.parseInt(pageSizeStr);
		}
		//组装查询条件
		books searchModel = new books();
		searchModel.setName(bookName);
		
		//调用service获取查询结果
		booksDAO booksDAO=new booksDAO();
		Pager<books> result = booksDAO.getAllItems(searchModel, pageNum, pageSize);
		System.out.println("总数"+result.getTotalPage());
		//返回结果到页面
		response.setHeader("Cache-Control", "no-cache");

		response.setHeader("Pragma", "no-cache");

		// 设置超时时间为0

		response.addDateHeader("Expires", 0);

		// 设置编码格式为utf-8

		response.setContentType("text/html;charset=utf-8");

		// 获取查询数据的json格式

		String responseStr = JSON.toJSONString(result);

		Writer writer = response.getWriter();

		writer.write(responseStr);

		writer.flush();
	}

	
}
