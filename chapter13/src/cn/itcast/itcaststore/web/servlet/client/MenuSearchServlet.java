package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.itcaststore.domain.PageBean;
import cn.itcast.itcaststore.service.ProductService;

/**
 * Servlet implementation class MenuSearchServlet
 */
public class MenuSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int currentPage=1;
		String _currentPage=request.getParameter("currentPage");
		if(_currentPage != null) {
			currentPage=Integer.parseInt(_currentPage);
		}
		int currentCount=4;
		String _currentCount=request.getParameter("currentCount");
		if(_currentCount != null) {
			currentCount=Integer.parseInt(_currentCount);
		}
		String searchfield=request.getParameter("textfield");
		if("«Î ‰»Î È√˚".equals(searchfield)) {
			request.getRequestDispatcher("/showProductByPage").forward(request, response);
			return;
		}
		ProductService service=new ProductService();
		PageBean bean=service.findBookByName(currentPage,currentCount,searchfield);
		request.setAttribute("bean", bean);
		request.getRequestDispatcher("/client/product_search_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
