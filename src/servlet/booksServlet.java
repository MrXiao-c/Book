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
		//����request��Ĳ���
		String bookName = request.getParameter("bookName");
		System.out.println("servlet"+bookName);
		
		
		
		
		//У��pageNum��������ĺϷ���
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
		//��װ��ѯ����
		books searchModel = new books();
		searchModel.setName(bookName);
		
		//����service��ȡ��ѯ���
		booksDAO booksDAO=new booksDAO();
		Pager<books> result = booksDAO.getAllItems(searchModel, pageNum, pageSize);
		System.out.println("����"+result.getTotalPage());
		//���ؽ����ҳ��
		response.setHeader("Cache-Control", "no-cache");

		response.setHeader("Pragma", "no-cache");

		// ���ó�ʱʱ��Ϊ0

		response.addDateHeader("Expires", 0);

		// ���ñ����ʽΪutf-8

		response.setContentType("text/html;charset=utf-8");

		// ��ȡ��ѯ���ݵ�json��ʽ

		String responseStr = JSON.toJSONString(result);

		Writer writer = response.getWriter();

		writer.write(responseStr);

		writer.flush();
	}

	
}
